package devdeeds.com.rrszoo.Kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rrszoo.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.share.Sharer
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.squareup.picasso.Picasso
import devdeeds.com.rrszoo.Fragments.ChangeLanguageSlide
import kotlinx.android.synthetic.main.activity_settings.view.*
import java.lang.Exception
import kotlin.collections.ArrayList

class AnimalPage : AppCompatActivity() {
    var btnShareLink: Button? = null
    var callbackManager: CallbackManager? = null
    var shareDialog: ShareDialog? = null
    var shareText = ""
    var imageUri = ""
    var zooLanguage: ZooLanguage? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var name: TextView? = null
    private var location: TextView? = null
    private var lifetime: TextView? = null
    private var food: TextView? = null
    private var numOfChildres: TextView? = null
    private var gettingExtraAnimal: String? = null
    private var gettingExtraAdmin: String? = null
    private var messageToServer: ArrayList<String?>? = null
    private var mt: GetInformation? = null
    var switchStringLanguage:String?="En"
    private var actionbar: ActionBar? = null


    var imageContentTarget: com.squareup.picasso.Target = object : com.squareup.picasso.Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            val sharePhoto: SharePhoto = SharePhoto.Builder()
                .setBitmap(bitmap)
                .build()
            val content: SharePhotoContent = SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build()
            if (shareDialog!!.canShow(content)) {
                shareDialog!!.show(content)
            } else {
                val linkContent: ShareLinkContent = ShareLinkContent.Builder()
                    .setQuote(shareText)
                    .setShareHashtag(ShareHashtag.Builder().setHashtag("#RRsZoo").build())
                    .setContentUrl(Uri.parse(imageUri)).build()
                shareDialog!!.show(linkContent)
            }

        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            TODO("Not yet implemented")
        }

        fun onBitmapFailed(errorDrawable: Drawable) {
            Log.e(TAG, errorDrawable.toString())
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            Log.e(TAG, "PrepareLoad")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))
        setContentView(if (zooLanguage?.isLTRLanguage == true) R.layout.animalpage else R.layout.animalpage_heb)

        //actionbar
        this.actionbar = supportActionBar!!
        //set actionbar title


        actionbar!!.title = "Animal Select Page"
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        messageToServer = ArrayList()
        gettingExtraAnimal = intent.getStringExtra("Animal")
        Log.e(
            TAG,
            "onCreate: Animal $gettingExtraAnimal"
        )
        gettingExtraAdmin = intent.getStringExtra("Admin")
        Log.e(TAG, "onCreate: Admin $gettingExtraAdmin")


        val backToAnimalSelection = findViewById<View>(R.id.backAnimal) as Button
        backToAnimalSelection.visibility = View.INVISIBLE
        backToAnimalSelection.setOnClickListener {
            val intent = Intent(applicationContext, MainPageOfAnimalChoose::class.java)
            intent.putExtra("Admin", gettingExtraAdmin)
            startActivity(intent)
        }


        if (intent.getStringExtra("Language") != null)
            switchStringLanguage = intent.getStringExtra("Language")

        //Make Language Fragments All the Time
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()




        fromDB

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private val fromDB: Unit
        @RequiresApi(Build.VERSION_CODES.GINGERBREAD) private get() {
            if (zooLanguage!!.isLTRLanguage) {
                messageToServer!!.add("En")
            } else {
                messageToServer!!.add("He")
            }
            messageToServer!!.add("Animal")
            messageToServer!!.add(gettingExtraAnimal)
            mt = GetInformation(messageToServer!!, this)
            mt!!.connect()
        }

    @SuppressLint("WrongThread")
    fun SetAnimalFromDataBase(animalMessage: ArrayList<String?>) {


        ///Animal animal = new Animal(animalMessage);
        Log.e(TAG, "SetAnimalFromDataBase: [0]=" + animalMessage[0])
        Log.e(TAG, "SetAnimalFromDataBase: [1]=" + animalMessage[1])
        name = findViewById<View>(R.id.name) as TextView
        name!!.append(animalMessage[1])
        location = findViewById<View>(R.id.location) as TextView
        location!!.append(animalMessage[2])
        lifetime = findViewById<View>(R.id.lifeTime) as TextView
        lifetime!!.append(animalMessage[3])
        food = findViewById<View>(R.id.food) as TextView
        food!!.append(animalMessage[4])
        numOfChildres = findViewById<View>(R.id.numOfChilds) as TextView
        numOfChildres!!.append(animalMessage[5])
        imageUri = animalMessage[6].toString()
        val ivBasicImage = findViewById<View>(R.id.animalImageView) as ImageView
        Picasso.get().load(imageUri).into(ivBasicImage)
        shareAnimal()
        val facebookText = findViewById<View>(R.id.facebookText) as TextView
        val titleAnimalPage: TextView = findViewById<TextView>(R.id.titleAnimalPage)
        fragmentTransaction!!.add(R.id.languageFragment, ChangeLanguageSlide(switchStringLanguage!!, zooLanguage, arrayListOf(
            TranslateObject(name!!, name?.text.toString()),
            TranslateObject(location!!, location?.text.toString()),
            TranslateObject(lifetime!!, lifetime?.text.toString()),
            TranslateObject(food!!, food?.text.toString()),
            TranslateObject(facebookText, facebookText.text.toString()),
            TranslateObject(titleAnimalPage, titleAnimalPage?.text.toString()),
            TranslateObject(numOfChildres!!, numOfChildres?.text.toString())
        ), actionbar)).commit()
    }

    fun shareAnimal() {
        FacebookSdk.sdkInitialize(this.applicationContext)
        btnShareLink = findViewById<View>(R.id.fbshare) as Button
        callbackManager = CallbackManager.Factory.create()
        shareDialog = ShareDialog(this)
        shareDialog!!.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result?> {
            override fun onSuccess(result: Sharer.Result?) {
                Log.e(TAG, "Shared successfully")
            }

            override fun onCancel() {
                Log.e(TAG, "Shared canceled!")
            }

            override fun onError(error: FacebookException) {
                Log.e(TAG, "Error")
            }
        })
        btnShareLink!!.setOnClickListener {
            val facebookText = findViewById<View>(R.id.facebookText) as TextView
            shareText = facebookText.text.toString()
            Picasso.get()
                .load("https://cdn1-www.superherohype.com/assets/uploads/2013/11/batmane3-1.jpg")
                .into(imageContentTarget)
        }
    }

    companion object {
        private const val TAG = "AnimalPage"
    }


}
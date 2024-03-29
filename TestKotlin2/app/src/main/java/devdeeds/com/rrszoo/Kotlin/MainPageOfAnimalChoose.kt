package devdeeds.com.rrszoo.Kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rrszoo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import devdeeds.com.rrszoo.Fragments.ChangeLanguageSlide
import devdeeds.com.rrszoo.Fragments.FragmentAddAnimal
import devdeeds.com.rrszoo.Fragments.FragmentAnimals
import kotlinx.android.synthetic.main.fragment_change_language_slide.view.*
import kotlinx.android.synthetic.main.main_page.*
import java.util.*
import kotlin.collections.ArrayList


class MainPageOfAnimalChoose() : AppCompatActivity(), OnItemSelectedListener{
    private var seaAnimal: Button? = null
    private var mammals: Button? = null
    private var reptalis: Button? = null
    private var birds: Button? = null
    private var arthropoda: Button? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var spinnerAnimals: Spinner? = null
    private var spinnerTypes: Spinner? = null
    private var fragmentAnimalPage: Fragment? = null
    private var fragmentAddAnimalal: FragmentAddAnimal? = null
    private var imageView: ImageView? = null
    private var gettingExtra: String? = null
    private var animal: ArrayList<String?>? = null
    private var messageToServer: ArrayList<String?>? = null
    private var getInformation: GetInformation? = null
    private var sendInformation: SendInformation? = null
    private var fab: FloatingActionButton? = null
    private var menu: Menu? = null
    private var inflater: MenuInflater? = null
    private var sharedPreference: SharedPreferences? = null
    private var editor: Editor? = null
    private var toogle: Switch? = null
    var zooLanguage: ZooLanguage? = null
    var switchStringLanguage:String?="En"
    private var actionbar:ActionBar?=null
    private var changeLanguageSlide: ChangeLanguageSlide? = null
    private var adapter: ArrayAdapter<String>? = null
    private var originalTypes: ArrayList<String?>? = null
    private var selectAnimalButton: Button? = null
    private var translateObjectArr = arrayListOf<TranslateObject>()


    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editor = getSharedPreferences("RRsZoo", MODE_PRIVATE).edit()
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))
        setContentView(if (zooLanguage!!.isLTRLanguage) R.layout.main_page else R.layout.main_page_heb)
        fab = findViewById(R.id.fab)
        imageView = findViewById<View>(R.id.titleBar3) as ImageView
        seaAnimal = findViewById<View>(R.id.seaAnimals) as Button
        mammals = findViewById<View>(R.id.mammals) as Button
        reptalis = findViewById<View>(R.id.reptiles) as Button
        birds = findViewById<View>(R.id.birds) as Button
        arthropoda = findViewById<View>(R.id.arthropoda) as Button
        animal = ArrayList()
        messageToServer = ArrayList()




        if (intent.getStringExtra("Language") != null)
            switchStringLanguage = intent.getStringExtra("Language")

        //For Admin Add new Animal to DataBase
        gettingExtra = intent.getStringExtra("Admin")
        Log.e(
            TAG,
            "onCreate: Login Admin $gettingExtra"
        )
        if (gettingExtra == "true") {
            fab?.setOnClickListener(View.OnClickListener {
                fabFunc()
            })

        } else {
            fab?.setVisibility(View.INVISIBLE)
        }

        //actionbar
        actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Main Page"
        val abc = actionbar
        this.adapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            arrayListOf<String>()
        )
        //Make Language Fragments All the Time
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        Log.e(TAG, "Main Page $switchStringLanguage")
        this.initTranslateObjectArr()
        fragmentTransaction!!.add(
            R.id.languageFragment, ChangeLanguageSlide(
                switchStringLanguage!!,
                zooLanguage!!,
                translateObjectArr,
                abc,
                adapter!!
            )
        ).commit()




        //todd: implement this and tanslate nav
        menuInflater
    }

    fun initTranslateObjectArr() {
        translateObjectArr.clear()
        translateObjectArr.add(TranslateObject(mammals!!, mammals?.text.toString()))
        translateObjectArr.add(TranslateObject(reptalis!!, reptalis?.text.toString()))
        translateObjectArr.add(TranslateObject(birds!!, birds?.text.toString()))
        translateObjectArr.add(TranslateObject(arthropoda!!, arthropoda?.text.toString()))
        translateObjectArr.add(TranslateObject(seaAnimal!!, seaAnimal?.text.toString()))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        backToAnimalMenu()
        return true
    }

    override fun onBackPressed() {
        moveTaskToBack(false)
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun animalSelection(view: View) {
        fragmentManager = supportFragmentManager
        fragmentAnimalPage = FragmentAnimals(
            zooLanguage?.isLTRLanguage == true,
            zooLanguage!!.getLang()
        )
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.animalFrag, fragmentAnimalPage as FragmentAnimals).addToBackStack(
            null
        ).commit()
        fragmentManager!!.executePendingTransactions()
        if (zooLanguage?.isLTRLanguage == true) {
            when (view.id) {
                R.id.seaAnimals -> getDataBaseTypes("Sea Animals")
                R.id.arthropoda -> getDataBaseTypes("Arthropoda")
                R.id.mammals -> getDataBaseTypes("Mammals")
                R.id.reptiles -> getDataBaseTypes("Reptiles")
                R.id.birds -> getDataBaseTypes("Birds")
            }
        } else {
            when (view.id) {
                R.id.seaAnimals -> getDataBaseTypes("חיות מים")
                R.id.arthropoda -> getDataBaseTypes("חסרי חוליות")
                R.id.mammals -> getDataBaseTypes("יונקים")
                R.id.reptiles -> getDataBaseTypes("זוחלים")
                R.id.birds -> getDataBaseTypes("עופות")
            }
        }


        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }
    fun backToAnimalMenu() {
        fragmentManager!!.popBackStack()
        imageView!!.visibility = View.VISIBLE
        seaAnimal!!.visibility = View.VISIBLE
        mammals!!.visibility = View.VISIBLE
        reptalis!!.visibility = View.VISIBLE
        birds!!.visibility = View.VISIBLE
        arthropoda!!.visibility = View.VISIBLE
        showFab()
        supportActionBar!!.show()
        actionbar!!.setDisplayHomeAsUpEnabled(false)
        actionbar!!.setDisplayHomeAsUpEnabled(false)

    }


    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    private fun getDataBaseTypes(animal: String) {
        if (zooLanguage?.isLTRLanguage == true) {
            messageToServer!!.add("En")
        } else {
            messageToServer!!.add("He")
        }
        messageToServer!!.add("Type")
        messageToServer!!.add(animal)
        getInformation = GetInformation(messageToServer!!, this)
        getInformation!!.connect()
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun fillArrayToSpinner(list: ArrayList<String?>?) {
        //TODO
        animal = list
        openSpinner(animal)
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    private fun openSpinner(types: ArrayList<String?>?) {
        originalTypes = types
        spinnerAnimals = findViewById(R.id.spinner)
        adapter?.clear()
        adapter!!.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinnerAnimals?.setAdapter(adapter)
        spinnerAnimals?.setOnItemSelectedListener(this)
        seaAnimal!!.visibility = View.INVISIBLE
        mammals!!.visibility = View.INVISIBLE
        reptalis!!.visibility = View.INVISIBLE
        birds!!.visibility = View.INVISIBLE
        arthropoda!!.visibility = View.INVISIBLE
        fab!!.visibility = View.INVISIBLE
        val language: View = findViewById(R.id.languageFragment)
        if (language.swithLanguages.isChecked) {
            types!!.forEach { type -> ZooTranslator.translate(
                type!!,
                zooLanguage!!.getLang(),
                adapter!!
            ) }
        } else {
            adapter!!.addAll(types!!)
        }
    }

    fun fabFunc() {
        fragmentManager = supportFragmentManager
        fragmentAddAnimalal = FragmentAddAnimal(zooLanguage?.isLTRLanguage == true)
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        if (zooLanguage?.isLTRLanguage == true) {
            fragmentTransaction.add(R.id.addAnimalFrag, fragmentAddAnimalal!!).addToBackStack(null).commit()
        } else {
            fragmentTransaction.replace(R.id.addAnimalFragHeb, fragmentAddAnimalal!!).addToBackStack(
                null
            ).commit()
        }
        fab!!.visibility = View.INVISIBLE
        imageView!!.visibility = View.INVISIBLE
        seaAnimal!!.visibility = View.INVISIBLE
        mammals!!.visibility = View.INVISIBLE
        reptalis!!.visibility = View.INVISIBLE
        birds!!.visibility = View.INVISIBLE
        arthropoda!!.visibility = View.INVISIBLE

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun addAnimal(view: View?) {
        val name = findViewById<View>(R.id.addAnimalName) as EditText
        val location = findViewById<View>(R.id.addLocation) as EditText
        val lifeTime = findViewById<View>(R.id.addLifeTime) as EditText
        val food = findViewById<View>(R.id.addFood) as EditText
        val childrens = findViewById<View>(R.id.addChildrens) as EditText
        val img = findViewById<View>(R.id.addImageLink) as EditText
        messageToServer!!.clear()
        if (name.text.toString().isEmpty() || location.text.toString()
                .isEmpty() || lifeTime.text.toString().isEmpty() || food.text.toString()
                .isEmpty() || childrens.text.toString().isEmpty() || img.text.toString().isEmpty()
        ) {
            openLoginAlert()
        } else {
            spinnerTypes = fragmentAddAnimalal?.spinner
            if (zooLanguage?.isLTRLanguage == true) {
                messageToServer!!.add("En")
            } else {
                messageToServer!!.add("He")
            }
            messageToServer!!.add("AddAnimal")
            messageToServer!!.add(spinnerTypes!!.selectedItem.toString())
            messageToServer!!.add(name.text.toString())
            messageToServer!!.add(location.text.toString())
            messageToServer!!.add(lifeTime.text.toString())
            messageToServer!!.add(food.text.toString())
            messageToServer!!.add(childrens.text.toString())
            messageToServer!!.add(img.text.toString())
            sendInformation = SendInformation(messageToServer!!, this@MainPageOfAnimalChoose)
            sendInformation!!.connect()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val animal = originalTypes!![position]
        selectAnimalButton = findViewById<View>(R.id.selectAnimal) as Button
        if (languageFragment.swithLanguages.isChecked) {
            ZooTranslator.translate(zooLanguage!!.getLang(), selectAnimalButton)
        } else {
            this.initTranslateObjectArr()
            translateObjectArr.add(
                TranslateObject(
                    selectAnimalButton!!,
                    selectAnimalButton!!.text.toString()
                )
            )
        }
        selectAnimalButton!!.setOnClickListener {
            val intent = Intent(applicationContext, AnimalPage::class.java)
            intent.putExtra("Animal", animal)
            intent.putExtra("Admin", gettingExtra)
            intent.putExtra("Language", switchStringLanguage)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    private fun showFab() {
        if (gettingExtra == "true") fab!!.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        inflater = menuInflater
        this.menu = menu
        inflater!!.inflate(R.menu.menu, menu)
        for (i in 0..this.menu!!.size()-1) {
            this.translateObjectArr.add(TranslateObject(this.menu!!.getItem(i), this.menu!!.getItem(i).title.toString()))
        }

        return true
    }

    @SuppressLint("NewApi")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val logout: String
        when (id) {
            R.id.logout -> {
                intent = Intent(applicationContext, FirstLoginPage::class.java)
                logout = "Logout"
                intent.putExtra("Logout", logout)
                logOut()
                editor!!.apply()
                startActivity(intent)
            }
            R.id.settings -> {
                intent = Intent(applicationContext, Settings::class.java)
                switchStringLanguage = zooLanguage!!.getLang();
                intent.putExtra("Language", switchStringLanguage)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item) //react to many chooses
        }
        return true
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun logOut() {
        editor!!.putBoolean("isLogOut", true)
        editor!!.putString("login", null)
        editor!!.putString("password", null)
        editor!!.putString("checked", null)
        editor!!.apply()
    }

    fun openLoginAlert() {
        val alertDialogBuilder = AlertDialog.Builder(this@MainPageOfAnimalChoose)
        alertDialogBuilder.setMessage("One or More cells are empty")
        alertDialogBuilder.setPositiveButton(
            "Ok"
        ) { arg0, arg1 -> Toast.makeText(
            this@MainPageOfAnimalChoose,
            "Fill all Text",
            Toast.LENGTH_LONG
        ).show() }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    companion object {
        private const val TAG = "MainPage"
    }
}
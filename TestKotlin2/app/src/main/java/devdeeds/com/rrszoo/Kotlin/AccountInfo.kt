package devdeeds.com.rrszoo.Kotlin


import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rrszoo.R
import com.google.mlkit.nl.translate.TranslateLanguage
import devdeeds.com.rrszoo.Fragments.ChangeLanguageSlide
import java.util.*
import kotlin.collections.ArrayList

class AccountInfo : AppCompatActivity() {
    private var getInformation: GetInformation? = null
    private var messageToServer: ArrayList<String?>? = null
    private var stringFromServer: ArrayList<String?>? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    var zooLanguage: ZooLanguage? = null
    private var menu: Menu? = null
    private var inflater: MenuInflater? = null
    var switchStringLanguage:String?="En"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))
        setContentView(if (zooLanguage?.isLTRLanguage == true) R.layout.activity_account_info else R.layout.activity_account_info_heb)
        accountInfo
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Settings"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        if (intent.getStringExtra("Language") != null)
            switchStringLanguage = intent.getStringExtra("Language")

        //Make Language Fragments All the Time
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        val name = findViewById<TextView>(R.id.nameAccDB)
        val email = findViewById<TextView>(R.id.emailAccDB)
        val admin = findViewById<TextView>(R.id.adminAccDB)
        val nameLabel = findViewById<TextView>(R.id.NameAcc)
        val emailLabel = findViewById<TextView>(R.id.emailAcc)
        val adminLabel = findViewById<TextView>(R.id.adminAcc)
        val accoutnInfoLabel = findViewById<TextView>(R.id.titleAcc)
        fragmentTransaction!!.add(R.id.languageFragment, ChangeLanguageSlide(switchStringLanguage!!, zooLanguage, arrayListOf(
            TranslateObject(name!!, name?.text.toString()),
            TranslateObject(admin!!, admin?.text.toString()),
            TranslateObject(email, email?.text.toString()),
            TranslateObject(nameLabel!!, nameLabel?.text.toString()),
            TranslateObject(emailLabel!!, emailLabel?.text.toString()),
            TranslateObject(adminLabel!!, adminLabel?.text.toString()),
            TranslateObject(accoutnInfoLabel!!, accoutnInfoLabel?.text.toString())
        ), actionbar)).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private val accountInfo: Unit
        @RequiresApi(Build.VERSION_CODES.GINGERBREAD) private get() {
            messageToServer = ArrayList()
            if (zooLanguage?.isLTRLanguage == true) {
                messageToServer?.add("En")
            } else {
                messageToServer?.add("He")
            }
            messageToServer?.add("Account")
            getInformation = GetInformation(messageToServer!!, this@AccountInfo)
            getInformation!!.connect()
        }

    fun setInfo(info: ArrayList<String?>) {
        stringFromServer = ArrayList()
        stringFromServer = info
        val name = findViewById<View>(R.id.nameAccDB) as TextView
        name.text = stringFromServer!![0]
        val email = findViewById<View>(R.id.emailAccDB) as TextView
        email.text = stringFromServer!![3]
        val admin = findViewById<View>(R.id.adminAccDB) as TextView
        if (stringFromServer!![2] == "true") admin.text = "Yes" else admin.text = "No"
    }

 
}
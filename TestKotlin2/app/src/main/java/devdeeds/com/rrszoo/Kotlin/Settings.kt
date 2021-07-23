package devdeeds.com.rrszoo.Kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rrszoo.R
import devdeeds.com.rrszoo.Fragments.ChangeLanguageSlide
import devdeeds.com.rrszoo.RecyclervView.LanguagesRecyclerView

class Settings : AppCompatActivity() {

    private var languageSettingsButton: Button?=null
    private var accountInformationSettingsButton: Button?=null
    var switchStringLanguage:String?="En"
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    var zooLanguage: ZooLanguage? = null
    private var translateObjectArr = arrayListOf<TranslateObject>()
    private var actionbar: ActionBar?=null
    private var mainTitle: TextView?=null;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))

        languageSettingsButton = findViewById<Button>(R.id.settingLanguage)
        accountInformationSettingsButton = findViewById<Button>(R.id.settingAccountInformation)
        mainTitle = findViewById<Button>(R.id.settingTitle)


        if (intent.getStringExtra("Language") != null)
            switchStringLanguage = intent.getStringExtra("Language")


        //actionbar
        actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Main Page"
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)


        //Make Language Fragments All the Time
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        this.initTranslateObjectArr()
        fragmentTransaction!!.add(R.id.languageFragment, ChangeLanguageSlide(switchStringLanguage!!,zooLanguage!!,translateObjectArr,actionbar)).commit()


    }

    fun initTranslateObjectArr() {
        translateObjectArr.clear()
        translateObjectArr.add(TranslateObject(languageSettingsButton!!, languageSettingsButton?.text.toString()))
        translateObjectArr.add(TranslateObject(accountInformationSettingsButton!!, accountInformationSettingsButton?.text.toString()))
        translateObjectArr.add(TranslateObject(mainTitle!!, mainTitle?.text.toString()))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun language(view: View){
        intent = Intent (this, LanguagesRecyclerView::class.java)
        intent.putExtra("Language", switchStringLanguage)
        startActivity(intent)
    }

    fun accountInformation(view: View){
        intent = Intent(applicationContext, AccountInfo::class.java)
        intent.putExtra("Language", switchStringLanguage)
        startActivity(intent)
    }


}
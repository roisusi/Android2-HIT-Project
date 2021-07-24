package devdeeds.com.rrszoo.Kotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rrszoo.R
import devdeeds.com.rrszoo.Fragments.ChangeLanguageSlide
import devdeeds.com.rrszoo.Fragments.FragmentLogin
import devdeeds.com.rrszoo.Fragments.FragmentRegister
import kotlinx.android.synthetic.main.fragment_change_language_slide.view.*
import java.util.*
import kotlin.collections.ArrayList

class FirstLoginPage : AppCompatActivity() {
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var login: Button? = null
    private var register: Button? = null
    private var title: ImageView? = null
    private var messageToServer: ArrayList<String?>? = null
    private var stringFromServer: ArrayList<String?>? = null
    private var sendInformation: SendInformation? = null
    private var fragmentLogin: FragmentLogin? = null
    private var logout: String? = null
    var zooLanguage: ZooLanguage? = null
    private var actionbar: ActionBar?=null
    private var translateObjectArr = arrayListOf<TranslateObject>()
    var switchStringLanguage:String?="En"




    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))
        setContentView(if (zooLanguage?.isLTRLanguage == true) R.layout.activity_main else R.layout.activity_main_heb)
        login = findViewById<View>(R.id.loginFirstPage) as Button
        register = findViewById<View>(R.id.registerFirstPage) as Button
        title = findViewById<View>(R.id.titleBar) as ImageView
        messageToServer = ArrayList()
        var languagefrag = findViewById<View>(R.id.languageFragment)
        fragmentLogin = FragmentLogin(zooLanguage!!, translateObjectArr , languagefrag)

        this.initTranslateObjectArr()

        //Hide the Menu Bar
        //supportActionBar!!.hide()
        logout = getIntent().getStringExtra("Logout")


        //actionbar
        actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Login Page"


        //Make Language Fragments All the Time
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.add(R.id.languageFragment, ChangeLanguageSlide(switchStringLanguage!!,zooLanguage!!,translateObjectArr,actionbar)).commit()


    }

    fun initTranslateObjectArr() {
        translateObjectArr.clear()
        translateObjectArr.add(TranslateObject(login!!, login?.text.toString()))
        translateObjectArr.add(TranslateObject(register!!, register?.text.toString()))
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        backToMain()
        return true
    }

    fun registerUser(view: View?) {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        var languagefrag = findViewById<View>(R.id.languageFragment)
        fragmentTransaction!!.add(R.id.fragmentReg, FragmentRegister(zooLanguage!!, translateObjectArr, languagefrag)).addToBackStack(null).commit()
        login!!.visibility = View.INVISIBLE
        register!!.visibility = View.INVISIBLE
        title!!.visibility = View.INVISIBLE
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun loginFrag(view: View?) {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.add(R.id.fragmentLog, fragmentLogin!!).addToBackStack(null).commit()
        login!!.visibility = View.INVISIBLE
        register!!.visibility = View.INVISIBLE
        title!!.visibility = View.INVISIBLE

        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        this.initTranslateObjectArr()
    }

    ///backToMain
    fun backToMain() {
        if (fragmentManager!!.backStackEntryCount == 0) {
            finish()
        } else {
            fragmentManager!!.popBackStack()
            login!!.visibility = View.VISIBLE
            register!!.visibility = View.VISIBLE
            title!!.visibility = View.VISIBLE
        }

        actionbar!!.setDisplayHomeAsUpEnabled(false)
        actionbar!!.setDisplayHomeAsUpEnabled(false)
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun loginToServer(view: View?) {
        logout = null
        fragmentLogin!!.rememberLogin(logout)
        fragmentLogin!!.loginToServer()
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun register(view: View?) {
        messageToServer!!.clear()
        val login = findViewById<View>(R.id.loginReg) as EditText
        val pass = findViewById<View>(R.id.passReg) as EditText
        val email = findViewById<View>(R.id.emailReg) as EditText
        if (zooLanguage!!.isLTRLanguage) {
            messageToServer!!.add("En")
        } else {
            messageToServer!!.add("He")
        }
        messageToServer!!.add("Register")
        messageToServer!!.add(login.text.toString())
        messageToServer!!.add(pass.text.toString())
        messageToServer!!.add("false")
        messageToServer!!.add(email.text.toString())
        sendInformation = SendInformation(messageToServer!!, this@FirstLoginPage, view)
        sendInformation!!.connect()
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun postLogin(s: ArrayList<String?>?) {
        stringFromServer = s
        Log.e(TAG, "test: $stringFromServer")
        if (fragmentLogin!!.rememberLogin(logout)) {
            val intent = Intent(this, MainPageOfAnimalChoose::class.java).apply {
                putExtra("Admin", stringFromServer!![2])
            }

            startActivity(intent)
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
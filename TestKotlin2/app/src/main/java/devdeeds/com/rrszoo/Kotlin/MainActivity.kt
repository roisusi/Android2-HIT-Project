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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rrszoo.R
import devdeeds.com.rrszoo.Fragments.FragmentLogin
import devdeeds.com.rrszoo.Fragments.FragmentRegister
import java.util.*

class MainActivity : AppCompatActivity() {
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var login: Button? = null
    private var register: Button? = null
    private var title: ImageView? = null
    private var messageToServer: MutableList<String>? = null
    private var stringFromServer: List<String>? = null
    private val getInformation: GetInformation? = null
    private var sendInformation: SendInformation? = null
    private var fragmentLogin: FragmentLogin? = null
    private var intent = null;
    private var logout: String? = null
    private var zooLanguage: ZooLanguage? = null
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))
        setContentView(if (zooLanguage?.isEnglish == true) R.layout.activity_main else R.layout.activity_main_heb)
        login = findViewById<View>(R.id.loginFirstPage) as Button
        register = findViewById<View>(R.id.registerFirstPage) as Button
        title = findViewById<View>(R.id.titleBar) as ImageView
        messageToServer = ArrayList()
        fragmentLogin = FragmentLogin(zooLanguage?.isEnglish)

        //Hide the Menu Bar
        supportActionBar!!.hide()
        logout = getIntent().getStringExtra("Logout")
    }

    fun registerUser(view: View?) {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.add(R.id.fragmentReg, FragmentRegister(zooLanguage?.isEnglish == true))
            .addToBackStack(null).commit()
        login!!.visibility = View.INVISIBLE
        register!!.visibility = View.INVISIBLE
        title!!.visibility = View.INVISIBLE
    }

    fun loginFrag(view: View?) {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.add(R.id.fragmentLog, fragmentLogin!!).addToBackStack(null).commit()
        login!!.visibility = View.INVISIBLE
        register!!.visibility = View.INVISIBLE
        title!!.visibility = View.INVISIBLE
    }

    ///backToMain
    fun backToMain(view: View?) {
        Log.d(TAG, "onBackPressed: ")
        if (fragmentManager!!.backStackEntryCount == 0) {
            finish()
        } else {
            fragmentManager!!.popBackStack()
            login!!.visibility = View.VISIBLE
            register!!.visibility = View.VISIBLE
            title!!.visibility = View.VISIBLE
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun loginToServer(view: View?) {
        logout = null
        fragmentLogin!!.rememberLogin(logout)
        fragmentLogin!!.loginToServer()
    }

    fun register(view: View?) {
        messageToServer!!.clear()
        val login = findViewById<View>(R.id.loginReg) as EditText
        val pass = findViewById<View>(R.id.passReg) as EditText
        val email = findViewById<View>(R.id.emailReg) as EditText
        if (zooLanguage!!.isEnglish) {
            messageToServer!!.add("En")
        } else {
            messageToServer!!.add("He")
        }
        messageToServer!!.add("Register")
        messageToServer!!.add(login.text.toString())
        messageToServer!!.add(pass.text.toString())
        messageToServer!!.add("false")
        messageToServer!!.add(email.text.toString())
        sendInformation = SendInformation(messageToServer!!, this@MainActivity, view)
        sendInformation?.execute()
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun postLogin(s: List<String>?) {
        stringFromServer = s
        Log.e(TAG, "test: $stringFromServer")
        if (fragmentLogin!!.rememberLogin(logout)) {
            val intent = Intent(this, MainPage::class.java).apply {
                putExtra("Admin", stringFromServer!![2])
            }

            startActivity(intent)
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
package devdeeds.com.rrszoo.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.rrszoo.R
import com.google.mlkit.nl.translate.TranslateLanguage
import devdeeds.com.rrszoo.Kotlin.GetInformation
import devdeeds.com.rrszoo.Kotlin.TranslateObject
import devdeeds.com.rrszoo.Kotlin.ZooLanguage
import devdeeds.com.rrszoo.Kotlin.ZooTranslator
import kotlinx.android.synthetic.main.fragment_change_language_slide.view.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLogin : Fragment {
    // TODO: Rename and change types of parameters
    var mParam1: String? = null
    var mParam2: String? = null
    var pref: SharedPreferences? = null
    var editor: Editor? = null
    var loginText: EditText? = null
    var passText: EditText? = null
    var stringFromServer: List<String>? = null
    var messageToServer: ArrayList<String?>? = null
    var getInformation: GetInformation? = null
    var logout = false
    var checkBoxLogin: CheckBox? = null
    var log: String? = null
    var language: String = TranslateLanguage.ENGLISH
    var loginButton: Button? = null
    var backButton: Button? = null
    var translateObjects: ArrayList<TranslateObject>? = null
    var zooLanguage: ZooLanguage? = null
    var languageFragment: View? = null

    constructor() {
        // Required empty public constructor
    }
    constructor(zooLanguage: ZooLanguage, translateObjects: ArrayList<TranslateObject>, langFrag: View) {
        // Required empty public constructor
        this.zooLanguage = zooLanguage
        this.translateObjects = translateObjects
        this.languageFragment = langFrag
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
        stringFromServer = ArrayList()
        pref = requireContext().getSharedPreferences(
            "RRsZoo",
            Context.MODE_PRIVATE
        ) // for getting and save on computer
        editor = pref?.edit() // for editing
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_login, container, false)
        loginText = v.findViewById<View>(R.id.loginText) as EditText
        passText = v.findViewById<View>(R.id.passText) as EditText
        checkBoxLogin = v.findViewById<View>(R.id.rememberCB) as CheckBox
        loginButton = v.findViewById(R.id.loginFrag) as Button

        if (languageFragment?.swithLanguages!!.isChecked) {
            ZooTranslator.translate(loginButton!!.text.toString(), zooLanguage?.getLang()!!, loginButton as TextView)
            ZooTranslator.translate(loginText!!.text.toString(), zooLanguage?.getLang()!!, loginText as TextView)
            ZooTranslator.translate(passText!!.text.toString(), zooLanguage?.getLang()!!, passText as TextView)
            ZooTranslator.translate(checkBoxLogin!!.text.toString(), zooLanguage?.getLang()!!, checkBoxLogin as TextView)

        } else {
            translateObjects?.add(TranslateObject(loginButton!!, loginButton!!.text.toString()))
            translateObjects?.add(TranslateObject(loginText!!, loginText!!.text.toString()))
            translateObjects?.add(TranslateObject(passText!!, passText!!.text.toString()))
            translateObjects?.add(TranslateObject(checkBoxLogin!!, checkBoxLogin!!.text.toString()))
        }
        //return inflater.inflate(R.layout.fragment_login, container, false);
        return v
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun loginFromServer(s: List<String>?) {
        stringFromServer = s
        if (checkBoxLogin!!.isChecked) log = "Login"
        setLoginDetails(stringFromServer!![0], stringFromServer!![1], log)
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        log = pref!!.getString("checked", null)
        if (log != null && log != "Logout") {
            tryLogIn()
        } else {
            //clear cache so it not remember you
            editor!!.putString("login", null)
            editor!!.putString("password", null)
            editor!!.putString("checked", null)
            editor!!.apply()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun tryLogIn() {
        val login = pref!!.getString("login", null)
        val password = pref!!.getString("password", null)
        log = pref!!.getString("checked", null)
        if (login != null && password != null) {
            loginText!!.setText(login)
            passText!!.setText(password)
            setLoginDetails(login, password, log)
            if (log == "Login") {
                logout = true
                checkBoxLogin!!.isChecked = true
            }
        }
        if (logout) loginToServer()
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun setLoginDetails(login: String?, password: String?, log: String?) {
        editor!!.putString("login", login)
        editor!!.putString("password", password)
        editor!!.putString("checked", log)
        editor!!.apply()
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun cleanLoginDetails() { // call this when logout
        setLoginDetails(null, null, null)
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    fun rememberLogin(s: String?): Boolean {
        if (s != null && s == "Logout") {
            cleanLoginDetails()
            logout = false
        } else if (checkBoxLogin!!.isChecked) {
            logout = true
            setLoginDetails(loginText!!.text.toString(), passText!!.text.toString(), "Login")
        } else {
            logout = true
            setLoginDetails(loginText!!.text.toString(), passText!!.text.toString(), "Logout")
        }
        return logout
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun loginToServer() {
        messageToServer = ArrayList()
        messageToServer?.clear()
        messageToServer?.add("En")
        messageToServer?.add("Login")
        messageToServer?.add(loginText!!.text.toString())
        messageToServer?.add(passText!!.text.toString())

        if (loginText!!.text.toString() != "" && passText!!.text.toString() !="") {
            getInformation = GetInformation(messageToServer!!, requireActivity())
            getInformation!!.connect()
        }
        else
            Toast.makeText(context, "Please Enter Login And Password", Toast.LENGTH_SHORT).show()
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLogin.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): FragmentLogin {
            val fragment = FragmentLogin()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
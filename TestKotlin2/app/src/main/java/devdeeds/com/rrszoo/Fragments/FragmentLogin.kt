package devdeeds.com.rrszoo.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.rrszoo.R
import devdeeds.com.rrszoo.Kotlin.GetInformation
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLogin : Fragment {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var pref: SharedPreferences? = null
    private var editor: Editor? = null
    private var loginText: EditText? = null
    private var passText: EditText? = null
    private var stringFromServer: List<String>? = null
    private var messageToServer: MutableList<String>? = null
    private var getInformation: GetInformation? = null
    private var logout = false
    private var checkBoxLogin: CheckBox? = null
    private var log: String? = null
    var isEnglish: Boolean? = null

    constructor() {
        // Required empty public constructor
    }

    constructor(isEnglsih: Boolean?) {
        // Required empty public constructor
        isEnglish = isEnglsih
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
        val v: View = inflater.inflate(
            if (isEnglish!!) R.layout.fragment_login else R.layout.fragment_login_heb,
            container,
            false
        )
        loginText = v.findViewById<View>(R.id.loginText) as EditText
        passText = v.findViewById<View>(R.id.passText) as EditText
        checkBoxLogin = v.findViewById<View>(R.id.rememberCB) as CheckBox

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
    private fun tryLogIn() {
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
    private fun setLoginDetails(login: String?, password: String?, log: String?) {
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

    fun loginToServer() {
        messageToServer = ArrayList()
        messageToServer?.clear()
        if (isEnglish!!) {
            messageToServer?.add("En")
        } else {
            messageToServer?.add("He")
        }
        messageToServer?.add("Login")
        messageToServer?.add(loginText!!.text.toString())
        messageToServer?.add(passText!!.text.toString())
        getInformation = GetInformation(messageToServer!!, requireActivity())
        getInformation?.execute()
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

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
package devdeeds.com.rrszoo.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rrszoo.R
import devdeeds.com.rrszoo.Kotlin.TranslateObject
import devdeeds.com.rrszoo.Kotlin.ZooLanguage
import devdeeds.com.rrszoo.Kotlin.ZooTranslator
import kotlinx.android.synthetic.main.fragment_change_language_slide.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRegister.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRegister : Fragment {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var zooLanguage: ZooLanguage? = null
    var translateObjects: ArrayList<TranslateObject>? = null
    var langFrag: View? = null

    constructor() {
        // Required empty public constructor
    }

    constructor(zooLanguage: ZooLanguage, translateObjects: ArrayList<TranslateObject>, langFrag: View) {
        this.zooLanguage = zooLanguage
        this.translateObjects = translateObjects
        this.langFrag = langFrag
        // Required empty public constructor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        var loginReg = view.findViewById<View>(R.id.loginReg) as EditText
        var passReg = view.findViewById<View>(R.id.passReg) as EditText
        var emailReg = view.findViewById<View>(R.id.emailReg) as EditText
        var sendButton = view.findViewById<View>(R.id.regToDataBase) as Button

        if (langFrag?.swithLanguages!!.isChecked) {
            ZooTranslator.translate(loginReg!!.text.toString(), zooLanguage?.getLang()!!, loginReg)
            ZooTranslator.translate(passReg!!.text.toString(), zooLanguage?.getLang()!!, passReg)
            ZooTranslator.translate(emailReg!!.text.toString(), zooLanguage?.getLang()!!, emailReg)
            ZooTranslator.translate(sendButton!!.text.toString(), zooLanguage?.getLang()!!, sendButton)

        } else {
            translateObjects?.add(TranslateObject(loginReg!!, loginReg!!.text.toString()))
            translateObjects?.add(TranslateObject(passReg!!, passReg!!.text.toString()))
            translateObjects?.add(TranslateObject(emailReg!!, emailReg!!.text.toString()))
            translateObjects?.add(TranslateObject(sendButton!!, sendButton!!.text.toString()))
        }

        return view
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
         * @return A new instance of fragment FragmentRegister.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): FragmentRegister {
            val fragment = FragmentRegister()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
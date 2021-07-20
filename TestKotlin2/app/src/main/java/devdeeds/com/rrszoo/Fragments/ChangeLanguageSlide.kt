package devdeeds.com.rrszoo.Fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.annotation.RequiresApi
import com.example.rrszoo.R
import com.google.mlkit.nl.translate.TranslateLanguage
import devdeeds.com.rrszoo.Kotlin.TranslateObject
import devdeeds.com.rrszoo.Kotlin.ZooLanguage
import devdeeds.com.rrszoo.Kotlin.ZooTranslator
import androidx.appcompat.app.ActionBar


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChangeLanguageSlide.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangeLanguageSlide(switchInitialization:String, zooLanguage: ZooLanguage?, translateObjects: ArrayList<TranslateObject>?) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var switchString: String?= null
    var zooLanguage: ZooLanguage? = null
    var translateObjects: ArrayList<TranslateObject>? = null
    var actionBar: ActionBar? = null


    init {
        switchString = switchInitialization
        this.zooLanguage = zooLanguage
        this.translateObjects = translateObjects
    }

    constructor(switchInitialization:String, zooLanguage: ZooLanguage?, translateObjects: ArrayList<TranslateObject>?, actionBar:ActionBar?):
            this(switchInitialization,zooLanguage,translateObjects){
                this.actionBar = actionBar


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }



    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(
            R.layout.fragment_change_language_slide ,
            container,
            false
        )
        var switch: Switch =  v.findViewById(R.id.swithLanguages)
        Log.e("From Frag Lan", "onCreateView: $switchString", )


        switch.setText("EN")
        switch.setOnClickListener {
            if(switch.isChecked) {
                switch.setText(zooLanguage!!.getLang().toUpperCase())
                translateObjects?.forEach { translateObjects: TranslateObject -> ZooTranslator.translate(translateObjects.text, zooLanguage!!.getLang(), translateObjects.view) }
                if (this.actionBar != null) {
                    this.actionBar!!.title = ZooTranslator.translate(this.actionBar!!.title.toString(), zooLanguage!!.getLang(), this.actionBar)
                }
            }
            else {
                switch.setText("EN")
                translateObjects?.forEach { translateObjects: TranslateObject -> ZooTranslator.translate(translateObjects.text, TranslateLanguage.ENGLISH, translateObjects.view) }
                if (this.actionBar != null) {
                    this.actionBar!!.title = ZooTranslator.translate(this.actionBar!!.title.toString(), TranslateLanguage.ENGLISH, this.actionBar)
                }
            }

        }

        //return inflater.inflate(R.layout.fragment_change_language_slide, container, false)
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChangeLanguageSlide.
         */
        // TODO: Rename and change types and number of paramete-rs
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangeLanguageSlide("",null,null).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
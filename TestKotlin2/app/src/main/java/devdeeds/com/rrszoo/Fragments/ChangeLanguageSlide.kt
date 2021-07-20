package devdeeds.com.rrszoo.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.example.rrszoo.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChangeLanguageSlide.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChangeLanguageSlide(switchInitialization:String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var switchString: String?= null

    init {
        switchString = switchInitialization
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }



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

        switch.setText("En")
        switch.setOnClickListener {
            if(switch.isChecked)
                switch.setText(switchString)
            else
                switch.setText("En")
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
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangeLanguageSlide("").apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package devdeeds.com.rrszoo.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.rrszoo.R
import com.google.mlkit.nl.translate.TranslateLanguage
import devdeeds.com.rrszoo.Kotlin.ZooTranslator


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAnimals.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAnimals : Fragment {
    var isLTRLanguage = true
    var language: String = TranslateLanguage.ENGLISH

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    constructor() {
        // Required empty public constructor
    }

    constructor(isLTRLanguage: Boolean, language: String) {
        // Required empty public constructor
        this.isLTRLanguage = isLTRLanguage
        this.language = language
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(
            if (isLTRLanguage) R.layout.fragment_animals else R.layout.fragment_animals_heb,
            container,
            false
        );
        return view;
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
         * @return A new instance of fragment FragmentAnimals.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): FragmentAnimals {
            val fragment = FragmentAnimals()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
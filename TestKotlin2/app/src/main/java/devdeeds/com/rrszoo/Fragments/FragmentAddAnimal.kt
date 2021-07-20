package devdeeds.com.rrszoo.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rrszoo.R

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAddAnimal.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAddAnimal : Fragment {
    var isEnglish = true

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var spinner: Spinner? = null
        private set

    constructor() {
        // Required empty public constructor
    }

    constructor(isEnglish: Boolean) {
        this.isEnglish = isEnglish
        // Required empty public constructor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }

        //Hide the Menu Bar
        //(activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(
            if (isEnglish) R.layout.fragment_add_animal else R.layout.fragment_add_animal_heb,
            container,
            false
        )
        spinner = v.findViewById(R.id.spinnerType)
        val adapter = ArrayAdapter.createFromResource(
            this.requireActivity(),
            R.array.Type,
            R.layout.support_simple_spinner_dropdown_item
        )
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner?.setAdapter(adapter)
        //spinnerTypes.setOnItemSelectedListener(this);
        return v
        //return inflater.inflate(R.layout.fragment_add_animal, container, false);
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
         * @return A new instance of fragment FragmentAddAnimal.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): FragmentAddAnimal {
            val fragment = FragmentAddAnimal()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
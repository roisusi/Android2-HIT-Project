package devdeeds.com.rrszoo.RecyclervView

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.rrszoo.R

import devdeeds.com.rrszoo.Kotlin.MainPageOfAnimalChoose
import kotlinx.android.synthetic.main.items_language_bottuns_list.view.*

class LanguagesAdapter(

    var dataClassOfLanguages:List<DataClassOfLanguages>


): RecyclerView.Adapter<LanguagesAdapter.TodoViewHolder>()
{
    inner class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    private var ListOfStates: MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        //get the next item to view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_language_bottuns_list,parent,false)

        if (ListOfStates.size ==0) {
            ListOfStates?.add("Af")
            ListOfStates?.add("Ar")
            ListOfStates?.add("Be")
            ListOfStates?.add("Bg")
            ListOfStates?.add("Bn")
            ListOfStates?.add("Ca")
            ListOfStates?.add("Cs")
            ListOfStates?.add("Cy")
            ListOfStates?.add("Da")
            ListOfStates?.add("De")
            ListOfStates?.add("El")
            ListOfStates?.add("En")
            ListOfStates?.add("Eo")
            ListOfStates?.add("Es")
            ListOfStates?.add("Et")
            ListOfStates?.add("Fa")
            ListOfStates?.add("Fi")
            ListOfStates?.add("Fr")
            ListOfStates?.add("Ga")
            ListOfStates?.add("Gl")
            ListOfStates?.add("Gu")
            ListOfStates?.add("He")
            ListOfStates?.add("Hi")
            ListOfStates?.add("Hr")
            ListOfStates?.add("Ht")
            ListOfStates?.add("Hu")
            ListOfStates?.add("Id")
            ListOfStates?.add("Is")
            ListOfStates?.add("It")
            ListOfStates?.add("Ja")
            ListOfStates?.add("Ka")
            ListOfStates?.add("Kn")
            ListOfStates?.add("Ko")
            ListOfStates?.add("Lt")
            ListOfStates?.add("Lv")
            ListOfStates?.add("Mk")
            ListOfStates?.add("Mr")
            ListOfStates?.add("Ms")
            ListOfStates?.add("Mt")
            ListOfStates?.add("Nl")
            ListOfStates?.add("No")
            ListOfStates?.add("Pl")
            ListOfStates?.add("Pt")
            ListOfStates?.add("Ro")
            ListOfStates?.add("Ru")
            ListOfStates?.add("Sk")
            ListOfStates?.add("Sl")
            ListOfStates?.add("Sq")
            ListOfStates?.add("Sv")
            ListOfStates?.add("Sw")
            ListOfStates?.add("Ta")
            ListOfStates?.add("Te")
            ListOfStates?.add("Th")
            ListOfStates?.add("Tl")
            ListOfStates?.add("Tr")
            ListOfStates?.add("Uk")
            ListOfStates?.add("Ur")
            ListOfStates?.add("Vi")
            ListOfStates?.add("Zh")
        }

        return TodoViewHolder(view)

    }

    @SuppressLint("WrongViewCast")
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        //take the data and show it
        holder.itemView.apply {
            lanButton.text = dataClassOfLanguages[position].nameOfButtonLanguage

            //                                                  //
            // When you Click on Button is say what you clicked //
            //                                                  //

            lanButton.setOnClickListener {

                var setLanguageToSwitch:String = ListOfStates?.get(position).toString()//lanButton.text as String
                Log.e("TAG", "Lan: $setLanguageToSwitch", )
                var intent = Intent(context, MainPageOfAnimalChoose::class.java)
                intent.putExtra("Admin","true")
                intent.putExtra("Language",setLanguageToSwitch)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        //count the items
        return dataClassOfLanguages.size
    }
}
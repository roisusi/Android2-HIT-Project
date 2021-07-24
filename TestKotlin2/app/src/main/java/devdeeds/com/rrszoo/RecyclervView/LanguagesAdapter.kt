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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        //get the next item to view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_language_bottuns_list,parent,false)
        var todos = TodoViewHolder(view)
        return todos

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

                var setLanguageToSwitch:String = dataClassOfLanguages[position].lang
                dataClassOfLanguages[position].zooLanguage.setLanguage(setLanguageToSwitch);
                Log.e("TAG", "Lan: ${setLanguageToSwitch.toUpperCase()}")
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
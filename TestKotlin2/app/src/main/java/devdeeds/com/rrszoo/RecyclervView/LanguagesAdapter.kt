package devdeeds.com.rrszoo.RecyclervView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rrszoo.R
import kotlinx.android.synthetic.main.item_todo.view.*

class LanguagesAdapter(

    var dataClassOfLanguages:List<DataClassOfLanguages>

): RecyclerView.Adapter<LanguagesAdapter.TodoViewHolder>()
{
    inner class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        //get the next item to view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)

        return TodoViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        //take the data and show it
        holder.itemView.apply {
            lanButton.text = dataClassOfLanguages[position].nameOfButtonLanguage

            //                                                  //
            // When you Click on Button is say what you clicked //
            //                                                  //

            lanButton.setOnClickListener {
                Toast.makeText(this.context,"You click on ${lanButton.text}",Toast.LENGTH_SHORT).show()

            }

        }

    }

    override fun getItemCount(): Int {
        //count the items
        return dataClassOfLanguages.size
    }
}
package devdeeds.com.rrszoo.RecyclervView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rrszoo.R
import kotlinx.android.synthetic.main.activity_languages_recycler_view.*


class LanguagesRecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages_recycler_view)

        var todoList = mutableListOf(
            Todo("Afrikaans"),
            Todo("Arabic"),
            Todo("Belarusian"),
            Todo("Bulgarian"),
            Todo("Bengali"),
            Todo("Catalan"),
            Todo("Czech"),
            Todo("Welsh"),
            Todo("Danish"),
            Todo("German"),
            Todo("Greek"),
            Todo("English"),
            Todo("Esperanto"),
            Todo("Spanish"),
            Todo("Estonian"),
            Todo("Persian"),
            Todo("Finnish"),
            Todo("French"),
            Todo("Irish"),
            Todo("Galician"),
            Todo("Gujarati"),
            Todo("Hebrew"),
            Todo("Hindi"),
            Todo("Croatian"),
            Todo("Haitian"),
            Todo("Hungarian"),
            Todo("Indonesian"),
            Todo("Icelandic"),
            Todo("Italian"),
            Todo("Japanese"),
            Todo("Georgian"),
            Todo("Kannada"),
            Todo("Korean"),
            Todo("Lithuanian"),
            Todo("Latvian"),
            Todo("Macedonian"),
            Todo("Marathi"),
            Todo("Malay"),
            Todo("Maltese"),
            Todo("Dutch"),
            Todo("Norwegian"),
            Todo("Polish"),
            Todo("Portuguese"),
            Todo("Romanian"),
            Todo("Russian"),
            Todo("Slovak"),
            Todo("Slovenian"),
            Todo("Albanian"),
            Todo("Swedish"),
            Todo("Swahili"),
            Todo("Tamil"),
            Todo("Telugu"),
            Todo("Thai"),
            Todo("Tagalog"),
            Todo("Turkish"),
            Todo("Ukrainian"),
            Todo("Urdu"),
            Todo("Vietnamese"),
            Todo("Chinese"),


        )

        var adapter = TodoAdapter(todoList)
        languagesRcyView1.adapter = adapter
        languagesRcyView1.layoutManager = LinearLayoutManager(this)




//        btnAddTodo.setOnClickListener{
//            val title = etTodo.text.toString()

//            val todo = Todo(title,false)
//            todoList.add(todo)
//            adapter.notifyItemInserted(todoList.size-1)
//        }
    }
}
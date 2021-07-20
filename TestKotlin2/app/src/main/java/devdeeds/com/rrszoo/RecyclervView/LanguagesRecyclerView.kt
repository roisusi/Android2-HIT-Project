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
            DataClassOfLanguages("Afrikaans"),
            DataClassOfLanguages("Arabic"),
            DataClassOfLanguages("Belarusian"),
            DataClassOfLanguages("Bulgarian"),
            DataClassOfLanguages("Bengali"),
            DataClassOfLanguages("Catalan"),
            DataClassOfLanguages("Czech"),
            DataClassOfLanguages("Welsh"),
            DataClassOfLanguages("Danish"),
            DataClassOfLanguages("German"),
            DataClassOfLanguages("Greek"),
            DataClassOfLanguages("English"),
            DataClassOfLanguages("Esperanto"),
            DataClassOfLanguages("Spanish"),
            DataClassOfLanguages("Estonian"),
            DataClassOfLanguages("Persian"),
            DataClassOfLanguages("Finnish"),
            DataClassOfLanguages("French"),
            DataClassOfLanguages("Irish"),
            DataClassOfLanguages("Galician"),
            DataClassOfLanguages("Gujarati"),
            DataClassOfLanguages("Hebrew"),
            DataClassOfLanguages("Hindi"),
            DataClassOfLanguages("Croatian"),
            DataClassOfLanguages("Haitian"),
            DataClassOfLanguages("Hungarian"),
            DataClassOfLanguages("Indonesian"),
            DataClassOfLanguages("Icelandic"),
            DataClassOfLanguages("Italian"),
            DataClassOfLanguages("Japanese"),
            DataClassOfLanguages("Georgian"),
            DataClassOfLanguages("Kannada"),
            DataClassOfLanguages("Korean"),
            DataClassOfLanguages("Lithuanian"),
            DataClassOfLanguages("Latvian"),
            DataClassOfLanguages("Macedonian"),
            DataClassOfLanguages("Marathi"),
            DataClassOfLanguages("Malay"),
            DataClassOfLanguages("Maltese"),
            DataClassOfLanguages("Dutch"),
            DataClassOfLanguages("Norwegian"),
            DataClassOfLanguages("Polish"),
            DataClassOfLanguages("Portuguese"),
            DataClassOfLanguages("Romanian"),
            DataClassOfLanguages("Russian"),
            DataClassOfLanguages("Slovak"),
            DataClassOfLanguages("Slovenian"),
            DataClassOfLanguages("Albanian"),
            DataClassOfLanguages("Swedish"),
            DataClassOfLanguages("Swahili"),
            DataClassOfLanguages("Tamil"),
            DataClassOfLanguages("Telugu"),
            DataClassOfLanguages("Thai"),
            DataClassOfLanguages("Tagalog"),
            DataClassOfLanguages("Turkish"),
            DataClassOfLanguages("Ukrainian"),
            DataClassOfLanguages("Urdu"),
            DataClassOfLanguages("Vietnamese"),
            DataClassOfLanguages("Chinese"),

        )

        var adapter = LanguagesAdapter(todoList)
        languagesRcyView1.adapter = adapter
        languagesRcyView1.layoutManager = LinearLayoutManager(this)

    }
}
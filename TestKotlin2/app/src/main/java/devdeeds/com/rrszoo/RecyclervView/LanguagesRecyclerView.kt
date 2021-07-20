package devdeeds.com.rrszoo.RecyclervView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rrszoo.R
import com.google.mlkit.nl.translate.TranslateLanguage
import devdeeds.com.rrszoo.Kotlin.ZooLanguage
import kotlinx.android.synthetic.main.activity_languages_recycler_view.*


class LanguagesRecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages_recycler_view)
        val zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))

        var todoList = mutableListOf(
            DataClassOfLanguages("Afrikaans", TranslateLanguage.AFRIKAANS, zooLanguage),
            DataClassOfLanguages("Arabic", TranslateLanguage.ARABIC, zooLanguage),
            DataClassOfLanguages("Belarusian", TranslateLanguage.BELARUSIAN, zooLanguage),
            DataClassOfLanguages("Bulgarian", TranslateLanguage.BULGARIAN, zooLanguage),
            DataClassOfLanguages("Bengali", TranslateLanguage.BENGALI, zooLanguage),
            DataClassOfLanguages("Catalan", TranslateLanguage.CATALAN, zooLanguage),
            DataClassOfLanguages("Czech", TranslateLanguage.CZECH, zooLanguage),
            DataClassOfLanguages("Welsh", TranslateLanguage.WELSH, zooLanguage),
            DataClassOfLanguages("Danish", TranslateLanguage.DANISH, zooLanguage),
            DataClassOfLanguages("German", TranslateLanguage.GERMAN, zooLanguage),
            DataClassOfLanguages("Greek", TranslateLanguage.GREEK, zooLanguage),
            DataClassOfLanguages("English", TranslateLanguage.ENGLISH, zooLanguage),
            DataClassOfLanguages("Esperanto", TranslateLanguage.ESPERANTO, zooLanguage),
            DataClassOfLanguages("Spanish", TranslateLanguage.SPANISH, zooLanguage),
            DataClassOfLanguages("Estonian", TranslateLanguage.ESTONIAN, zooLanguage),
            DataClassOfLanguages("Persian", TranslateLanguage.PERSIAN, zooLanguage),
            DataClassOfLanguages("Finnish", TranslateLanguage.FINNISH, zooLanguage),
            DataClassOfLanguages("French", TranslateLanguage.FRENCH, zooLanguage),
            DataClassOfLanguages("Irish", TranslateLanguage.IRISH, zooLanguage),
            DataClassOfLanguages("Galician", TranslateLanguage.GALICIAN, zooLanguage),
            DataClassOfLanguages("Gujarati", TranslateLanguage.GUJARATI, zooLanguage),
            DataClassOfLanguages("Hebrew", TranslateLanguage.HEBREW, zooLanguage),
            DataClassOfLanguages("Hindi", TranslateLanguage.HINDI, zooLanguage),
            DataClassOfLanguages("Croatian", TranslateLanguage.CROATIAN, zooLanguage),
            DataClassOfLanguages("Haitian", TranslateLanguage.HAITIAN_CREOLE, zooLanguage),
            DataClassOfLanguages("Hungarian", TranslateLanguage.HUNGARIAN, zooLanguage),
            DataClassOfLanguages("Indonesian", TranslateLanguage.INDONESIAN, zooLanguage),
            DataClassOfLanguages("Icelandic", TranslateLanguage.ICELANDIC, zooLanguage),
            DataClassOfLanguages("Italian", TranslateLanguage.ITALIAN, zooLanguage),
            DataClassOfLanguages("Japanese", TranslateLanguage.JAPANESE, zooLanguage),
            DataClassOfLanguages("Georgian", TranslateLanguage.GEORGIAN, zooLanguage),
            DataClassOfLanguages("Kannada", TranslateLanguage.KANNADA, zooLanguage),
            DataClassOfLanguages("Korean", TranslateLanguage.KOREAN, zooLanguage),
            DataClassOfLanguages("Lithuanian", TranslateLanguage.LITHUANIAN, zooLanguage),
            DataClassOfLanguages("Latvian", TranslateLanguage.LATVIAN, zooLanguage),
            DataClassOfLanguages("Macedonian", TranslateLanguage.MACEDONIAN, zooLanguage),
            DataClassOfLanguages("Marathi", TranslateLanguage.MARATHI, zooLanguage),
            DataClassOfLanguages("Malay", TranslateLanguage.MALAY, zooLanguage),
            DataClassOfLanguages("Maltese", TranslateLanguage.MALTESE, zooLanguage),
            DataClassOfLanguages("Dutch", TranslateLanguage.DUTCH, zooLanguage),
            DataClassOfLanguages("Norwegian", TranslateLanguage.NORWEGIAN, zooLanguage),
            DataClassOfLanguages("Polish", TranslateLanguage.POLISH, zooLanguage),
            DataClassOfLanguages("Portuguese", TranslateLanguage.PORTUGUESE, zooLanguage),
            DataClassOfLanguages("Romanian", TranslateLanguage.ROMANIAN, zooLanguage),
            DataClassOfLanguages("Russian", TranslateLanguage.RUSSIAN, zooLanguage),
            DataClassOfLanguages("Slovak", TranslateLanguage.SLOVAK, zooLanguage),
            DataClassOfLanguages("Slovenian", TranslateLanguage.SLOVENIAN, zooLanguage),
            DataClassOfLanguages("Albanian", TranslateLanguage.ALBANIAN, zooLanguage),
            DataClassOfLanguages("Swedish", TranslateLanguage.SWEDISH, zooLanguage),
            DataClassOfLanguages("Swahili", TranslateLanguage.SWAHILI, zooLanguage),
            DataClassOfLanguages("Tamil", TranslateLanguage.TAMIL, zooLanguage),
            DataClassOfLanguages("Telugu", TranslateLanguage.TELUGU, zooLanguage),
            DataClassOfLanguages("Thai", TranslateLanguage.THAI, zooLanguage),
            DataClassOfLanguages("Tagalog", TranslateLanguage.TAGALOG, zooLanguage),
            DataClassOfLanguages("Turkish", TranslateLanguage.TURKISH, zooLanguage),
            DataClassOfLanguages("Ukrainian", TranslateLanguage.UKRAINIAN, zooLanguage),
            DataClassOfLanguages("Urdu", TranslateLanguage.URDU, zooLanguage),
            DataClassOfLanguages("Vietnamese", TranslateLanguage.VIETNAMESE, zooLanguage),
            DataClassOfLanguages("Chinese", TranslateLanguage.CHINESE, zooLanguage)
        )

        var adapter = LanguagesAdapter(todoList)
        languagesRcyView1.adapter = adapter
        languagesRcyView1.layoutManager = LinearLayoutManager(this)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Settings"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
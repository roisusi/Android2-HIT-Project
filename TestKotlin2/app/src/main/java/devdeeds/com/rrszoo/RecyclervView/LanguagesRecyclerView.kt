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
            Todo("Afrikaans", TranslateLanguage.AFRIKAANS, zooLanguage),
            Todo("Arabic", TranslateLanguage.ARABIC, zooLanguage),
            Todo("Belarusian", TranslateLanguage.BELARUSIAN, zooLanguage),
            Todo("Bulgarian", TranslateLanguage.BULGARIAN, zooLanguage),
            Todo("Bengali", TranslateLanguage.BENGALI, zooLanguage),
            Todo("Catalan", TranslateLanguage.CATALAN, zooLanguage),
            Todo("Czech", TranslateLanguage.CZECH, zooLanguage),
            Todo("Welsh", TranslateLanguage.WELSH, zooLanguage),
            Todo("Danish", TranslateLanguage.DANISH, zooLanguage),
            Todo("German", TranslateLanguage.GERMAN, zooLanguage),
            Todo("Greek", TranslateLanguage.GREEK, zooLanguage),
            Todo("English", TranslateLanguage.ENGLISH, zooLanguage),
            Todo("Esperanto", TranslateLanguage.ESPERANTO, zooLanguage),
            Todo("Spanish", TranslateLanguage.SPANISH, zooLanguage),
            Todo("Estonian", TranslateLanguage.ESTONIAN, zooLanguage),
            Todo("Persian", TranslateLanguage.PERSIAN, zooLanguage),
            Todo("Finnish", TranslateLanguage.FINNISH, zooLanguage),
            Todo("French", TranslateLanguage.FRENCH, zooLanguage),
            Todo("Irish", TranslateLanguage.IRISH, zooLanguage),
            Todo("Galician", TranslateLanguage.GALICIAN, zooLanguage),
            Todo("Gujarati", TranslateLanguage.GUJARATI, zooLanguage),
            Todo("Hebrew", TranslateLanguage.HEBREW, zooLanguage),
            Todo("Hindi", TranslateLanguage.HINDI, zooLanguage),
            Todo("Croatian", TranslateLanguage.CROATIAN, zooLanguage),
            Todo("Haitian", TranslateLanguage.HAITIAN_CREOLE, zooLanguage),
            Todo("Hungarian", TranslateLanguage.HUNGARIAN, zooLanguage),
            Todo("Indonesian", TranslateLanguage.INDONESIAN, zooLanguage),
            Todo("Icelandic", TranslateLanguage.ICELANDIC, zooLanguage),
            Todo("Italian", TranslateLanguage.ITALIAN, zooLanguage),
            Todo("Japanese", TranslateLanguage.JAPANESE, zooLanguage),
            Todo("Georgian", TranslateLanguage.GEORGIAN, zooLanguage),
            Todo("Kannada", TranslateLanguage.KANNADA, zooLanguage),
            Todo("Korean", TranslateLanguage.KOREAN, zooLanguage),
            Todo("Lithuanian", TranslateLanguage.LITHUANIAN, zooLanguage),
            Todo("Latvian", TranslateLanguage.LATVIAN, zooLanguage),
            Todo("Macedonian", TranslateLanguage.MACEDONIAN, zooLanguage),
            Todo("Marathi", TranslateLanguage.MARATHI, zooLanguage),
            Todo("Malay", TranslateLanguage.MALAY, zooLanguage),
            Todo("Maltese", TranslateLanguage.MALTESE, zooLanguage),
            Todo("Dutch", TranslateLanguage.DUTCH, zooLanguage),
            Todo("Norwegian", TranslateLanguage.NORWEGIAN, zooLanguage),
            Todo("Polish", TranslateLanguage.POLISH, zooLanguage),
            Todo("Portuguese", TranslateLanguage.PORTUGUESE, zooLanguage),
            Todo("Romanian", TranslateLanguage.ROMANIAN, zooLanguage),
            Todo("Russian", TranslateLanguage.RUSSIAN, zooLanguage),
            Todo("Slovak", TranslateLanguage.SLOVAK, zooLanguage),
            Todo("Slovenian", TranslateLanguage.SLOVENIAN, zooLanguage),
            Todo("Albanian", TranslateLanguage.ALBANIAN, zooLanguage),
            Todo("Swedish", TranslateLanguage.SWEDISH, zooLanguage),
            Todo("Swahili", TranslateLanguage.SWAHILI, zooLanguage),
            Todo("Tamil", TranslateLanguage.TAMIL, zooLanguage),
            Todo("Telugu", TranslateLanguage.TELUGU, zooLanguage),
            Todo("Thai", TranslateLanguage.THAI, zooLanguage),
            Todo("Tagalog", TranslateLanguage.TAGALOG, zooLanguage),
            Todo("Turkish", TranslateLanguage.TURKISH, zooLanguage),
            Todo("Ukrainian", TranslateLanguage.UKRAINIAN, zooLanguage),
            Todo("Urdu", TranslateLanguage.URDU, zooLanguage),
            Todo("Vietnamese", TranslateLanguage.VIETNAMESE, zooLanguage),
            Todo("Chinese", TranslateLanguage.CHINESE, zooLanguage)
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
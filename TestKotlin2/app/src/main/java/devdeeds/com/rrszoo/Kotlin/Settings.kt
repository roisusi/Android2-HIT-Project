package devdeeds.com.rrszoo.Kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.rrszoo.R
import devdeeds.com.rrszoo.RecyclervView.LanguagesRecyclerView

class Settings : AppCompatActivity() {

    private var languageSettingsButton: Button?=null
    private var accountInformationSettingsButton: Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        languageSettingsButton = findViewById<Button>(R.id.settingLanguage)
        accountInformationSettingsButton = findViewById<Button>(R.id.settingAccountInformation)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Main Page"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun language(view: View){
        val intent = Intent (this, LanguagesRecyclerView::class.java)
        startActivity(intent)
    }

    fun accountInformation(view: View){
        intent = Intent(applicationContext, AccountInfo::class.java)
        startActivity(intent)
    }


}
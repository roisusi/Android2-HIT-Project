package devdeeds.com.rrszoo.Kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }

    fun language(view: View){

        val intent = Intent (this, LanguagesRecyclerView::class.java)
        startActivity(intent)

    }


}
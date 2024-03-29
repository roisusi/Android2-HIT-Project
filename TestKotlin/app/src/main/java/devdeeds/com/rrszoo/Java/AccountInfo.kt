package devdeeds.com.rrszoo.Java


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import devdeeds.com.rrszoo.Java.ZooLanguage
import devdeeds.com.rrszoo.R
import java.util.*

class AccountInfo : AppCompatActivity() {
    private var getInformation: GetInformation? = null
    private val sendInformation: SendInformation? = null
    private var messageToServer: MutableList<String>? = null
    private var stringFromServer: List<String>? = null
    var zooLanguage: ZooLanguage? = null
    private var menu: Menu? = null
    private var inflater: MenuInflater? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zooLanguage = ZooLanguage(getSharedPreferences("RRsZoo", MODE_PRIVATE))
        setContentView(if (zooLanguage?.isEnglish == true) R.layout.activity_account_info else R.layout.activity_account_info_heb)
        accountInfo
    }

    fun backAcc(view: View?) {
        val intent = Intent(applicationContext, MainPage::class.java)
        intent.putExtra("Admin", stringFromServer!![2])
        startActivity(intent)
    }

    private val accountInfo: Unit
        private get() {
            messageToServer = ArrayList()
            if (zooLanguage?.isEnglish == true) {
                messageToServer?.add("En")
            } else {
                messageToServer?.add("He")
            }
            messageToServer?.add("Account")
            getInformation = GetInformation(messageToServer!!, this@AccountInfo)
            getInformation?.execute()
        }

    fun setInfo(info: List<String>) {
        stringFromServer = ArrayList()
        stringFromServer = info
        val name = findViewById<View>(R.id.nameAccDB) as TextView
        name.text = stringFromServer!![0]
        val email = findViewById<View>(R.id.emailAccDB) as TextView
        email.text = stringFromServer!![3]
        val admin = findViewById<View>(R.id.adminAccDB) as TextView
        if (stringFromServer!![2] == "true") admin.text = "Yes" else admin.text = "No"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        inflater = menuInflater
        this.menu = menu
        inflater!!.inflate(R.menu.change_language, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.Hebrew -> {
                zooLanguage?.setHebrew()
                setContentView(R.layout.activity_account_info_heb)
                setInfo(stringFromServer!!)
                item.isChecked = true
            }
            R.id.English -> {
                zooLanguage?.setEnglish()
                setContentView(R.layout.activity_account_info)
                setInfo(stringFromServer!!)
                item.isChecked = true
            }
            else -> return super.onOptionsItemSelected(item) //react to many chooses
        }
        return true
    }
}
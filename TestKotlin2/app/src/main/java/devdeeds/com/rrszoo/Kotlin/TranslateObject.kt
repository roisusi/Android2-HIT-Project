package devdeeds.com.rrszoo.Kotlin

import android.widget.TextView

class TranslateObject(view: TextView, text: String) {
    var view: TextView? = null
    var text: String = ""

    init {
        this.view = view
        this.text = text
    }

}
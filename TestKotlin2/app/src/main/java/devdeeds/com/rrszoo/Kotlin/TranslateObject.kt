package devdeeds.com.rrszoo.Kotlin

import android.view.MenuItem
import android.widget.TextView

class TranslateObject(text: String) {
    var view: TextView? = null
    var menuItem: MenuItem? = null
    var text: String = ""

    init {
        this.text = text
    }

    constructor(view: TextView, text: String): this(text) {
        this.view = view
    }

    constructor(menuItem: MenuItem, text: String): this(text) {
        this.menuItem = menuItem
    }

}
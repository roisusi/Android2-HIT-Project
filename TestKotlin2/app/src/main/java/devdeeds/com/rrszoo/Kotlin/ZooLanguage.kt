package devdeeds.com.rrszoo.Kotlin

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class ZooLanguage internal constructor(  // false equals hebrew
    private val sharedPreferences: SharedPreferences
) {
    private val editore: Editor
    val isEnglish: Boolean
        public get() = sharedPreferences.getBoolean("language", true)

    @SuppressLint("NewApi")
    fun setEnglish() {
        editore.putBoolean("language", true)
        editore.apply()
    }

    @SuppressLint("NewApi")
    fun setHebrew() {
        editore.putBoolean("language", false)
        editore.apply()
    }

    init {
        editore = sharedPreferences.edit()
    }
}
package devdeeds.com.rrszoo.Kotlin

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class ZooLanguage internal constructor(  // false equals hebrew
    private val sharedPreferences: SharedPreferences
) {
    private val editore: Editor
    private val RTLLanguages: Array<String> = arrayOf<String>(TranslateLanguage.HEBREW, TranslateLanguage.ARABIC);

    val isLTRLanguage: Boolean
        public get() = !(RTLLanguages.contains(getLang()))

    @SuppressLint("NewApi")
    fun setLanguage(lang: String) {
        ZooTranslator.downloadModule(lang)
        editore.putString("language", lang)
        editore.apply()
    }

    fun getLang(): String {
        return sharedPreferences.getString("language", TranslateLanguage.ENGLISH)!!;
    }

    init {
        editore = sharedPreferences.edit()
    }
}
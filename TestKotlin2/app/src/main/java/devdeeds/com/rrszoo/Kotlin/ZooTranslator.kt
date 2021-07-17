package devdeeds.com.rrszoo.Kotlin

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import java.io.Console

class ZooTranslator {

    companion object {
        fun translate(str: String, sourceLng: String): String {
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(sourceLng)
                .build()
            val translator: Translator = Translation.getClient(options)
            translator.downloadModelIfNeeded().addOnSuccessListener {
                translator.translate(str).addOnSuccessListener {
                    var x = it;
                    System.out.println(x)
                }.addOnFailureListener({
                    System.out.println(it.toString())
                });
            }.addOnFailureListener {
                System.out.println(it.toString())
            }
            return "a";
        }
    }
}
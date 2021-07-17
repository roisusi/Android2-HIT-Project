package devdeeds.com.rrszoo.Kotlin

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import java.util.concurrent.atomic.AtomicReference

class ZooTranslator {

    companion object {
        @JvmStatic


        fun translate(str: String, sourceLng: String): String {
            val result = AsyncRunnable.wait(object : AsyncRunnable<String?>() {
                override fun run(notifier: AtomicReference<String?>) {
                    // here goes your async code, e.g.:
                    Thread {
                        run {
                            val options = TranslatorOptions.Builder()
                                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                                    .setTargetLanguage(sourceLng)
                                    .build()
                            val translator: Translator = Translation.getClient(options)
                            translator.downloadModelIfNeeded().addOnSuccessListener {
                                finish(notifier, "")
                                translator.translate(str).addOnSuccessListener {
                                    finish(notifier, it)
                                }.addOnFailureListener {
                                    System.out.println(it.toString())
                                    finish(notifier, it.toString())
                                };
                            }.addOnFailureListener {
                                System.out.println(it.toString())
                                finish(notifier, "This was a asynchronous call!")
                            }
                        }
                    }.start()
                }
            })
            return result!!;
        }
    }
}

/*

val options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(sourceLng)
                    .build()
            val translator: Translator = Translation.getClient(options)
            ZooTranslator.executor.execute(Runnable {
                translator.downloadModelIfNeeded().addOnSuccessListener {
                    translator.translate(str).addOnSuccessListener {
                        callback(it.toString())
                    }.addOnFailureListener {
                        System.out.println(it.toString())
                        callback(it.toString())
                    };
                }.addOnFailureListener {
                    System.out.println(it.toString())
                    callback(it.toString())
                }
            })


var result = AsyncRunnable.wait(object : AsyncRunnable<String?>() {
    override fun run(notifier: AtomicReference<String?>?) {
        // here goes your async code, e.g.:
        Thread { finish(notifier, "This was a asynchronous call!") }.start()
    }
})!!


        fun translate(str: String, sourceLng: String): String {
            return Single.create<String> { emitter ->
                    val options = TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(sourceLng)
                            .build()
                    val translator: Translator = Translation.getClient(options)
                    executor.execute(Runnable {
                        translator.downloadModelIfNeeded().addOnSuccessListener {
                            translator.translate(str).addOnSuccessListener {
                                emitter.onSuccess(it)
                            }.addOnFailureListener {
                                System.out.println(it.toString())
                                emitter.onSuccess(str)
                            };
                        }.addOnFailureListener {
                            System.out.println(it.toString())
                            emitter.onSuccess(str)
                        }
                    })
            }.blockingGet()
        }
*/
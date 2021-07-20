package devdeeds.com.rrszoo.Kotlin

import androidx.appcompat.app.ActionBar
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class ZooTranslator {

    companion object {
        @JvmStatic

        fun downloadModule(lng: String) {
            val options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(lng)
                    .build()
            val translator: Translator = Translation.getClient(options)
            Thread {
                Tasks.await(translator.downloadModelIfNeeded()
                        .addOnSuccessListener {
                }.addOnFailureListener {
                    System.out.println(it.toString())
                });
            }.start()
        }

        fun translate(str: String, lng: String, view: TextView?): String {
            var isWaiting = false
            var result: String = str
            val options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(lng)
                    .build()
            val translator: Translator = Translation.getClient(options)
                Thread {
                            Tasks.await(translator.downloadModelIfNeeded().addOnSuccessListener {
                                translator.translate(str).addOnSuccessListener {
                                    isWaiting = true
                                    view?.setText(it)
                                    result = it;
                                    System.out.println(it.toString())
                                }.addOnFailureListener {
                                    System.out.println(it.toString())
                                    isWaiting = true
                                    result = it.toString();
                                };
                            }.addOnFailureListener {
                                System.out.println(it.toString())
                                isWaiting = true
                                result = it.toString();
                            });
                }.start()
            return result;
        }

        @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
        fun translate(str: String, lng: String, actionBar: ActionBar?): String {
            var isWaiting = false
            var result: String = str
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(lng)
                .build()
            val translator: Translator = Translation.getClient(options)
            Thread {
                Tasks.await(translator.downloadModelIfNeeded().addOnSuccessListener {
                    translator.translate(str).addOnSuccessListener {
                        isWaiting = true
                        actionBar?.title = it
                        result = it;
                        System.out.println(it.toString())
                    }.addOnFailureListener {
                        System.out.println(it.toString())
                        isWaiting = true
                        result = it.toString();
                    };
                }.addOnFailureListener {
                    System.out.println(it.toString())
                    isWaiting = true
                    result = it.toString();
                });
            }.start()
            return result;
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
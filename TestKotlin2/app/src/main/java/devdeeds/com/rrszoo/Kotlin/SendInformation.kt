package devdeeds.com.rrszoo.Kotlin

import android.app.Activity
import android.os.Build
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


@RequiresApi(Build.VERSION_CODES.GINGERBREAD)
class SendInformation(message: ArrayList<String?>, activity: Activity) {

    private var TAG:String = "GetInformation"
    private var ip:String = "10.0.2.2"
    private var okMessage:String = ""
    var backFromServer = ArrayList<String?>()
    private var sendToServer = ArrayList<String?>()
    private var activity:Activity=activity
    private var view: View? = null


    init {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        this.activity = activity
        sendToServer = message
    }

    constructor (message: ArrayList<String?>, activity: Activity, view: View?) : this(message,activity) {
        this.view = view!!
        this.activity = activity!!
        sendToServer = message!!
    }


    fun connect (){
        try {
            val s = Socket(ip, 20000) //connect to server at port 20000
            val pr = PrintWriter(s.getOutputStream(), true) //set the output stream
            val gson = Gson()
            val serializedLogIn = gson.toJson(sendToServer)
            pr.println(serializedLogIn)
            val inputStreamReader = InputStreamReader(s.getInputStream()) //to receive the data
            val bufferedReader = BufferedReader(inputStreamReader)
            okMessage = bufferedReader.readLine()
            if (okMessage != null) {
                backFromServer = gson.fromJson(okMessage, java.util.ArrayList::class.java) as ArrayList<String?>
            }
            Log.d(TAG, "myTask: is " + backFromServer)
            pr.flush()
            pr.close()
            sendToServer.clear()
            s.close()
            openActivity()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun openActivity() {

        Log.e(TAG, "onPostExecute: pre " + okMessage)
        if (activity is MainActivity && backFromServer.isEmpty()) {
            openLoginAlert()
        } else if (activity is MainActivity && backFromServer[0] == "OK") {
            val activity = activity as MainActivity
            Toast.makeText(activity, "Registration Success", Toast.LENGTH_LONG).show()
            activity.backToMain(view)
        } else if (activity is MainActivity && backFromServer[0] == "NOT OK") {
            Toast.makeText(activity, "User Already Exist", Toast.LENGTH_LONG).show()
        }

        if (activity is MainPage && backFromServer.isEmpty()) {
            openLoginAlert()
        } else if (activity is MainPage && backFromServer[0] == "OK") {
            val activity = activity as MainPage
            Toast.makeText(activity, "Add Success", Toast.LENGTH_LONG).show()
            activity.backToAnimalMenu(view)
        } else if (activity is MainPage && backFromServer[0] == "NOT OK") {
            Toast.makeText(activity, "Animal Already Exist", Toast.LENGTH_LONG).show()
        }

    }




    //-----------------//
    //Alert  For Login //
    //-----------------//
    fun openLoginAlert() {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setMessage("You have Enter Wrong User/Password")
        alertDialogBuilder.setPositiveButton("Ok"
        ) { arg0, arg1 -> Toast.makeText(activity, "Try Again", Toast.LENGTH_LONG).show() }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}

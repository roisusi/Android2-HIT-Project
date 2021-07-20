package devdeeds.com.rrszoo.Kotlin

import android.app.Activity
import android.os.Build
import android.os.StrictMode
import android.util.Log
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
class GetInformation(message: ArrayList<String?>, activity: Activity) {


    private var TAG:String = "GetInformation"
    private var ip:String = "10.0.2.2"
    private var okMessage:String = ""
    var backFromServer = ArrayList<String?>()
    private var sendToServer = ArrayList<String?>()
    private var activity:Activity=activity


    init {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        sendToServer = message
        Log.e(TAG, "There is : $sendToServer")
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
        } else if (activity is MainActivity) {
            val activity = activity as MainActivity?
            activity!!.postLogin(backFromServer)
        }

        if (activity is AnimalPage && backFromServer.isEmpty()) {
        } else if (activity is AnimalPage) {
            val activity = activity as AnimalPage?
            activity!!.SetAnimalFromDataBase(backFromServer)
        }

        if (activity is MainPage && backFromServer.isEmpty()) {
        } else if (activity is MainPage) {
            val activity = activity as MainPage?
            activity!!.fillArrayToSpinner(backFromServer)
        }

        if (activity is AccountInfo && backFromServer.isEmpty()) {
        } else if (activity is AccountInfo) {
            val activity = activity as AccountInfo?
            activity!!.setInfo(backFromServer)
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
        /*
              alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                  }
              });
      */
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }



}






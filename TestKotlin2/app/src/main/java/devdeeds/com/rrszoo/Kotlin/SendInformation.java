package devdeeds.com.rrszoo.Kotlin;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class SendInformation extends AsyncTask<String,Void,String> {

    private static final String TAG = "sendInformation";
    private static final String ip = "10.0.0.4";
    private static String okMessage="";
    private static List<String> backFromServer;
    private static List<String> sendToServer;
    private static Activity activity;
    private View view;

    public SendInformation(List<String> message, Activity activity) {

        this.backFromServer = new ArrayList<>();
        this.activity = activity;
        sendToServer = new ArrayList<>();
        this.sendToServer = message;
    }
    public SendInformation(List<String> message, Activity activity, View view) {
        this.view = view;
        this.backFromServer = new ArrayList<>();
        this.activity = activity;
        sendToServer = new ArrayList<>();
        this.sendToServer = message;
    }


    @Override
    protected String doInBackground(String... strings) {
        try{

            Socket s = new Socket(ip,20000); //connect to server at port 20000
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true); //set the output stream

            Gson gson = new Gson();
            String serializedLogIn = gson.toJson(sendToServer);
            pr.println(serializedLogIn);

            InputStreamReader inputStreamReader = new InputStreamReader(s.getInputStream()); //to receive the data
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            okMessage = bufferedReader.readLine();
            if (okMessage != null) {
                backFromServer = gson.fromJson(okMessage, ArrayList.class);
            }

            Log.d(TAG, "myTask: is " + backFromServer);

            pr.flush();
            pr.close();
            sendToServer.clear();
            s.close();


        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.e(TAG, "onPostExecute: pre "  + okMessage);

        Class a;

        if (activity instanceof MainActivity && backFromServer.isEmpty()) {
            openLoginAlert();
        }
        else if (activity instanceof MainActivity && backFromServer.get(0).equals("OK")){

            MainActivity activity = (MainActivity) this.activity;
            Toast.makeText(activity,"Registration Success",Toast.LENGTH_LONG).show();
            activity.backToMain(view);
        }
        else if (activity instanceof MainActivity && backFromServer.get(0).equals("NOT OK")){
            Toast.makeText(activity,"User Already Exist",Toast.LENGTH_LONG).show();

        }

        if (activity instanceof MainPage && backFromServer.isEmpty()) {
            openLoginAlert();
        }
        else if (activity instanceof MainPage && backFromServer.get(0).equals("OK")){

            MainPage activity = (MainPage) this.activity;
            Toast.makeText(activity,"Add Success",Toast.LENGTH_LONG).show();
            activity.backToAnimalMenu(view);
        }
        else if (activity instanceof MainPage && backFromServer.get(0).equals("NOT OK")){
            Toast.makeText(activity,"Animal Already Exist",Toast.LENGTH_LONG).show();
        }

        super.onPostExecute(s);
    }



    //-----------------//
    //Alert  For Login //
    //-----------------//
    public void openLoginAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setMessage("You have Enter Wrong User/Password");
        alertDialogBuilder.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(activity,"Try Again",Toast.LENGTH_LONG).show();
                }
            });
/*
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
*/
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}


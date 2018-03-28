package com.example.saravana.studentscorner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button sub;
    EditText roll;
    public String rollNo;
    public static String base="139.59.67.71/saec/public/api";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sub=(Button) findViewById(R.id.submit);
        roll=(EditText) findViewById(R.id.roll);

    }

    @Override
    protected void onStart() {
        super.onStart();



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                rollNo=roll.getText().toString();
                if(rollNo==null)
                {
                    Toast.makeText(MainActivity.this,"Enter Roll No",Toast.LENGTH_LONG).show();
                }else {
                new Login().execute(rollNo);
            }
            }
        });


    }


    private class Login extends AsyncTask<String, Void, String> {
        int rs2;


        @Override
        protected String doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String phon = params[0];


            // Will contain the raw JSON response as a string.
            String otp = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://" + base + "/login");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setReadTimeout(10000);
                urlConnection.connect();
                JSONObject cred = new JSONObject();
                try {
                    cred.put("roll_no", phon);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(cred.toString());
                wr.flush();
                rs2 = urlConnection.getResponseCode();
                Log.i("Response", String.valueOf(rs2));

                // Read the input stream into a String

                InputStream error ;
                if (rs2 == 422){
                    error=urlConnection.getErrorStream();
                    StringBuilder buffer = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(error));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                    }
                    otp = buffer.toString();
                    Log.i("json", "hiiiiiiiiiiiiiiiiiiii");
                    return otp;

                } else {
                    error=urlConnection.getInputStream();
                    StringBuilder buffer = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(error));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                    }
                    otp = buffer.toString();
                    Log.i("json", "hiiiiiiiiiiiiiiiiiiii");
                    return otp;
                }

            } catch (IOException e) {

                e.printStackTrace();
                return otp;

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String mm) {

            Log.i("response", String.valueOf(rs2));
            if(rs2==0||rs2>400)
            {
                if(rs2==422)
                {
                    JSONObject err= null;
                    try {
                        err = new JSONObject(mm);
                        String str = err.getString("message");
                        fun(str);
                        Log.i("word",mm);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }}
                else{
                    //startActivity(new Intent(Next.this,ServerError.class));
                }
            }
            else
            {
                try{
                    super.onPostExecute(mm);
                    Log.i("word",mm);
                    JSONObject j2 = new JSONObject(mm);
                    String otpCode = j2.getString("message");
                    Intent s=new Intent(MainActivity.this,Otp.class);
                    s.putExtra("otp",otpCode);
                    s.putExtra("rollNo",rollNo);
                    startActivity(s);

                   } catch (JSONException e) {
                    e.printStackTrace();
                }catch(NullPointerException m)
                {
                    //here to handle time out
                    fun("something went wrong! Try again");
                    m.printStackTrace();
                }




            }
        }
    }

    private void fun(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(msg);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

// Set other dialog properties

// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }














}

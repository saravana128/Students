package com.example.saravana.studentscorner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TestLink extends AppCompatActivity {
    ListView l2;
    public UsersAdapter2 adapter;
    public ArrayList<User2> arrayOfUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlink);
        l2 = (ListView) findViewById(R.id.list);
        new StudList().execute();

    }






    public class UsersAdapter2 extends ArrayAdapter<User2> {
        public UsersAdapter2(Context context, ArrayList<User2> sub) {
            super(context, 0, sub);
        }

        @Override
        public View getView(final int position2, View convertView2, ViewGroup parent2) {
            // Get the data item for this position
            final User2 user = getItem(position2);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView2 == null) {
                convertView2 = LayoutInflater.from(getContext()).inflate(R.layout.list_item2, parent2, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView2.findViewById(R.id.tv_title2);
            TextView tvHome = (TextView) convertView2.findViewById(R.id.tv_descr2);
            TextView staff = (TextView) convertView2.findViewById(R.id.staff);
            //LinearLayout l2=(LinearLayout) convertView2.findViewById(R.id.lis);
            CardView card = (CardView) convertView2.findViewById(R.id.card);
            // Populate the data into the template view using the data object
            tvName.setText(user.type );
            tvHome.setText(user.desc);
            staff.setText("By: "+user.staff_name);
            //onclick
            l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int sposition, long id) {

                    User2 fa = adapter.getItem(sposition);

                    Intent purna=new Intent(TestLink.this,Webpage.class);
                    purna.putExtra("url",fa.link);
                    startActivity(purna);



                }});

            // Return the completed view to render on screen
            return convertView2;
        }
    }

    public static class User2 {
        public String id, name, staff_id,link,desc,staff_name,type;
        public boolean check;

        //done
        public User2(String id, String name, String link) {
            this.id = id;
            this.name = name;
            this.link=link;
        }

        // Constructor to convert JSON object into a Java class instance
        public User2(JSONObject object) {

            try {
                this.id = object.getString("id");
                this.name = object.getString("name");
                this.staff_id=object.getString("staff_id");
                this.link= object.getString("test_link");
                this.desc= object.getString("description");
                this.staff_name=object.getJSONObject("staff").getString("first_name");
                this.type=object.getJSONObject("type").getString("name");
                this.check=false;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getLink() {
            return link;
        }

        public static ArrayList<User2> fromJson(JSONArray jsonObjects) {
            ArrayList<User2> users = new ArrayList<User2>();
            for (int i = 0; i < jsonObjects.length(); i++) {
                try {
                    users.add(new User2(jsonObjects.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return users;
        }
    }
    private class StudList extends AsyncTask<String, Void, String> {
        int rs2;


        @Override
        protected String doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String otp = null;
            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL("http://" + MainActivity.base + "/tests");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setReadTimeout(10000);
                urlConnection.connect();
                rs2 = urlConnection.getResponseCode();
                Log.i("Response", String.valueOf(rs2));

                // Read the input stream into a String

                InputStream error;
                if (rs2 == 422) {
                    error = urlConnection.getErrorStream();
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
                    error = urlConnection.getInputStream();
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
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            switch (rs2) {
                case 422:
                    JSONObject err = null;
                    try {
                        err = new JSONObject(s);
                        String str = err.getString("message");
                        fun(str);
                        Log.i("won", s);
                        rs2 = 0;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;


                case 200:
                case 201:
                    super.onPostExecute(s);
                    JSONArray m = null;
                    try {
                        m = new JSONArray(s);
                        arrayOfUsers = User2.fromJson(m);
                        adapter = new UsersAdapter2(TestLink.this, arrayOfUsers);
                        l2.setChoiceMode(l2.CHOICE_MODE_MULTIPLE);
                        l2.setAdapter(adapter);
                    } catch (JSONException e) {

                    }

                    Log.i("json", s);
                    rs2 = 0;
                    break;

                default:
                    break;

            }

        }
    }
    private void fun(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestLink.this);
        builder.setTitle(msg);
// Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

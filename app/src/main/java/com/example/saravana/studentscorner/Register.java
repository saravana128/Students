package com.example.saravana.studentscorner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {


    EditText fsn1;
    EditText lsn1;
    Button reg;
    Base64 d;
    public String fs1,ls1;
    public static String rend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        fsn1=(EditText) findViewById(R.id.fsn1);
        lsn1=(EditText) findViewById(R.id.lsn1);
        reg=(Button) findViewById(R.id.reg);
        String token=getIntent().getExtras().getString("token");
        String fname=getIntent().getExtras().getString("fname");
        String roll=getIntent().getExtras().getString("rollno");
        String id=getIntent().getExtras().getString("id");
        String ds=id+":"+token;
        final String ens=d.encodeToString(ds.getBytes(), Base64.NO_WRAP);
        //Log.i("encoded",ens);
        rend="Basic "+ens;
        SharedPreferences.Editor editor = getSharedPreferences("saravana", MODE_PRIVATE).edit();
        editor.putString("base64", rend);
        editor.commit();


    }
    @Override
    protected void onStart() {
        super.onStart();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fs1=fsn1.getText().toString();
                ls1=lsn1.getText().toString();
                Intent msd=new Intent(Register.this,Homepage.class);
                startActivity(msd);
            }
        });



    }
    }

package com.example.saravana.studentscorner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * Created by saravana on 8/1/18.
 */

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    ImageButton notes,marks,calen;
    ImageButton notifi;
    ImageButton review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        notes = (ImageButton) findViewById(R.id.notes);
        notifi = (ImageButton) findViewById(R.id.notifi);
        review = (ImageButton) findViewById(R.id.review);
        marks= (ImageButton) findViewById(R.id.marks);
        calen=(ImageButton) findViewById(R.id.asgn);
        notes.setOnClickListener(this);
        notifi.setOnClickListener(this);
        review.setOnClickListener(this);
        marks.setOnClickListener(this);
        calen.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.notes:
                Intent n=new Intent(Homepage.this,Notes.class);
                    startActivity(n);
                    break;
            case R.id.notifi:
                Intent n2=new Intent(Homepage.this,Notifiication.class);
                startActivity(n2);
                break;
            case R.id.review:
                Intent n3=new Intent(Homepage.this,TestLink.class);
                startActivity(n3);
                break;
            case R.id.marks:
                Intent n4=new Intent(Homepage.this,Webpage.class);
                n4.putExtra("url","https://drive.google.com/open?id=126xYX51_doGblYc0HDnh39_Wan6c5366");
                startActivity(n4);
                break;
            case R.id.asgn:
                Intent n5=new Intent(Homepage.this,Webpage.class);
                n5.putExtra("url","https://calendar.google.com/calendar/embed?src=digigurusaec%40gmail.com&ctz=Asia%2FCalcutta");
                startActivity(n5);
                break;
        }
    }
}

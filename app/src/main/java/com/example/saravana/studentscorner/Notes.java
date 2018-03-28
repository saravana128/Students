package com.example.saravana.studentscorner;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


public class Notes extends AppCompatActivity implements View.OnClickListener {

    Button sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        sem1=(Button) findViewById(R.id.oneSem);
        sem2=(Button) findViewById(R.id.twoSem);
        sem3=(Button) findViewById(R.id.threeSem);
        sem4=(Button) findViewById(R.id.fourSem);
        sem5=(Button) findViewById(R.id.fiveSem);
        sem6=(Button) findViewById(R.id.sixSem);
        sem7=(Button) findViewById(R.id.sevenSem);
        sem8=(Button) findViewById(R.id.eightSem);


        sem1.setOnClickListener(this);
        sem2.setOnClickListener(this);
        sem3.setOnClickListener(this);
        sem4.setOnClickListener(this);
        sem5.setOnClickListener(this);
        sem6.setOnClickListener(this);
        sem7.setOnClickListener(this);
        sem8.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent nee=new Intent(Notes.this,Webpage.class);
        switch (view.getId())
        {
            case R.id.oneSem:
                nee.putExtra("url","https://drive.google.com/open?id=1GU2v1aZHMjeqm5Ojz5WU7wPquwCQMevm");
                startActivity(nee);
                break;

            case R.id.twoSem:

                nee.putExtra("url","https://drive.google.com/open?id=17ZswAWhJM4rVV5256RK1r2y3mHw8YQJT");
                startActivity(nee);
                break;

            case R.id.threeSem:
                nee.putExtra("url","https://drive.google.com/open?id=1RMcnNqdwlz1d03et51EFWiLSnCHZdsJZ");
                startActivity(nee);
                break;

            case R.id.fourSem:
                nee.putExtra("url","https://drive.google.com/open?id=1oFA7RK4Fhjy0EbZK-lAjRtMRR7OaG5Pj");
                startActivity(nee);
                break;

            case R.id.fiveSem:
                nee.putExtra("url","https://drive.google.com/open?id=1_CQQ4rS5ZzzkPLYAiCp-2QKGnwq_Fo2m");
                startActivity(nee);
                break;

            case R.id.sixSem:
                nee.putExtra("url","https://drive.google.com/open?id=1kN4s0eaiBdkM06Kn2GsGpgK378HrXI-4");
                startActivity(nee);
                break;

            case R.id.sevenSem:
                nee.putExtra("url","https://drive.google.com/open?id=1o_gKiZ9OrflAh12R6gJ4LMkzsXxdYha3");
                startActivity(nee);
                break;

            case R.id.eightSem:
                nee.putExtra("url","https://drive.google.com/open?id=1fA0Z-ESidVP39Iht-merTU79G6mXZV1N");
                startActivity(nee);
                break;

        }

    }
}

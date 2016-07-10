package com.example.android.jetpack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

       /* TextView tx = (TextView)findViewById(R.id.improve);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/CircularStd_Book.otf");
        tx.setTypeface(custom_font);*/

        TextView lifeStyle = (TextView) findViewById(R.id.improve_lifestyle);
        lifeStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lifestyleIntent = new Intent(MainActivity.this, LifeStyleSuggestionActivity.class);
                startActivity(lifestyleIntent);
            }
        });

        TextView health = (TextView) findViewById(R.id.improve_health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent healthIntent = new Intent(MainActivity.this, HealthSuggestionActivity.class);
                startActivity(healthIntent);
            }
        });

        TextView learning = (TextView) findViewById(R.id.improve_learning);
        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent learningIntent = new Intent(MainActivity.this, LearningSuggestionActivity.class);
                startActivity(learningIntent);
            }
        });

        TextView writeOwn = (TextView) findViewById(R.id.improve_own);
        writeOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeOwnIntent = new Intent(MainActivity.this, WriteYourOwnActivity.class);
                startActivity(writeOwnIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeOwnIntent = new Intent(MainActivity.this, WriteYourOwnActivity.class);
                startActivity(writeOwnIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



}

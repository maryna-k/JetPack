package com.example.android.jetpack;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class HealthActivity1 extends AppCompatActivity implements View.OnTouchListener, AdapterView.OnItemClickListener{

    private EditText etext;
    private ListPopupWindow pw;
    private String[] list = { "Get out of bed","Brush my teeth","Lock the door","Finish the dishes","Eat","Get out of the shower", "Come home", "Come to school", "Return from classes", "Get my homework", "Clean the room", "Water flowers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health1);

        TextView tv1 = (TextView) findViewById(R.id.headerHealthAfter);
        Spanned result1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result1 = Html.fromHtml(getString(R.string.healthHeaderAfter),Html.FROM_HTML_MODE_LEGACY);
        } else {
            result1 = Html.fromHtml(getString(R.string.healthHeaderAfter));
        }
    tv1.setText(result1);

        TextView tv2 = (TextView) findViewById(R.id.healthAfterExplanation);
        Spanned result2;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result2 = Html.fromHtml(getString(R.string.healthExplanation),Html.FROM_HTML_MODE_LEGACY);
        } else {
            result2 = Html.fromHtml(getString(R.string.healthExplanation));
        }
        tv2.setText(result2);

        etext = (EditText) findViewById(R.id.afterI_suggestions);
        etext.setOnTouchListener(this);

        pw = new ListPopupWindow(this);
        pw.setAdapter(new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, list));
        pw.setAnchorView(etext);
        pw.setModal(true);
        pw.setOnItemClickListener(this);

        FloatingActionButton fab2_next = (FloatingActionButton) findViewById(R.id.fab2_next);
        fab2_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent healthNext = new Intent(HealthActivity1.this, HealthActivity2.class);
                String choice = etext.getText().toString();
                Log.v("String entered: ", choice);
                healthNext.putExtra("choice_to_display", choice);
                Log.v("String entered: ", choice);
                startActivity(healthNext);
            }
        });

        FloatingActionButton fab2_prev = (FloatingActionButton) findViewById(R.id.fab2_prev);
        fab2_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeOwnIntent = new Intent (HealthActivity1.this, MainActivity.class);
                startActivity(writeOwnIntent);
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = list[position];
        etext.setText(item);
        pw.dismiss();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getX() >= (v.getWidth() - ((EditText)v).getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                pw.show();
                return true;
            }
        }
        return false;
    }

}

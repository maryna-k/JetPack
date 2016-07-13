package com.example.android.jetpack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class HealthActivity2 extends AppCompatActivity implements View.OnTouchListener, AdapterView.OnItemClickListener {

    private EditText etext;
    private ListPopupWindow pw;
    private String[] list = { "do 5 sit-ups","perform 5 chin-ups","wash a plate","read a book for 5 minutes","brush my teeth","drink a glass of water", "go sleep", "open textbooks", "open my laptop", "write 5 sentences"};
    private String choice1;
    private String afterI = "\"After I ";

    private String almostFinalString; //string with "..."


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health2);

        TextView tv1 = (TextView) findViewById(R.id.headerHealth_page2);

        Bundle extras = getIntent().getExtras();
        choice1 = extras.getString("choice_to_display");

        //finalString = afterI + choice1.toLowerCase();

        almostFinalString = choice1.toLowerCase() + "...\"";

        choice1 = choice1.toLowerCase() + ", "; //String to send to the next activity ("do smth, ")

        setTextWithSpan(tv1, afterI, almostFinalString);

        TextView tv2 = (TextView) findViewById(R.id.healthExplanation_page2);
        Spanned result2;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result2 = Html.fromHtml(getString(R.string.healthExplanation2),Html.FROM_HTML_MODE_LEGACY);
        } else {
            result2 = Html.fromHtml(getString(R.string.healthExplanation2));
        }
        tv2.setText(result2);

        etext = (EditText) findViewById(R.id.iWill_suggestions);
        etext.setOnTouchListener(this);

        pw = new ListPopupWindow(this);
        pw.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        pw.setAnchorView(etext);
        pw.setModal(true);
        pw.setOnItemClickListener(this);


        FloatingActionButton fab_next = (FloatingActionButton) findViewById(R.id.fab3_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent healthNext = new Intent(HealthActivity2.this, HealthActivity3.class);
                String choice2 = etext.getText().toString();
                Log.v("String entered: ", choice2);
                healthNext.putExtra("choice_2", choice2);
                healthNext.putExtra("choice_1", choice1);
                startActivity(healthNext);
            }
        });

        FloatingActionButton fab_prev = (FloatingActionButton) findViewById(R.id.fab3_prev);
        fab_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent writeOwnIntent = new Intent(HealthActivity2.this, HealthActivity1.class);
                startActivity(writeOwnIntent);
            }
        });
    }

    void setTextWithSpan(TextView tv, String normalPart, String boldPart) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        sb.append(normalPart);
        int start = sb.length();
        sb.append(boldPart);
        sb.setSpan(new StyleSpan(Typeface.BOLD), start, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(sb);
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
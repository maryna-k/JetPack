package com.example.android.jetpack;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.TextView;

public class HealthActivity3 extends AppCompatActivity {

    private String choice1;
    private String afterI = "\"After I ";
    private String iWill = "I will ";
    private String choice2;

    private String finalString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health3);

        Bundle extras = getIntent().getExtras();
        choice1 = extras.getString("choice_1");
        choice2 = extras.getString("choice_2");
        choice2 = choice2.toLowerCase() + ".\"";
        Log.v("HealthActivity string: ", choice2);

        finalString = afterI + choice1 + iWill + choice2;

        TextView tv1 = (TextView) findViewById(R.id.health_habit);

        setTextWithSpan(tv1, afterI, choice1, iWill, choice2);



        TextView tv2 = (TextView) findViewById(R.id.health_habit_almost_done);
        Spanned result2;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result2 = Html.fromHtml(getString(R.string.youCanAdd),Html.FROM_HTML_MODE_LEGACY);
        } else {
            result2 = Html.fromHtml(getString(R.string.youCanAdd));
        }
        tv2.setText(result2);
    }

    void setTextWithSpan(TextView tv, String normalPart1, String boldPart1, String normalPart2, String boldPart2) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        sb.append(normalPart1);
        int start1 = sb.length();
        sb.append(boldPart1);
        sb.setSpan(new StyleSpan(Typeface.BOLD), start1, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        sb.append(normalPart2);
        int start2 = sb.length();
        sb.append(boldPart2);
        sb.setSpan(new StyleSpan(Typeface.BOLD), start2, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(sb);
    }
}

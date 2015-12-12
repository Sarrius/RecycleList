package com.example.sars2.a21v.RecycleView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sars2.a21v.R;

public class CardViewActivity extends Activity {

    TextView personName;
    TextView personAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        personName = (TextView)findViewById(R.id.person_name);
        personAge = (TextView)findViewById(R.id.person_age);


        personName.setText("");
        personAge.setText("");

    }
}

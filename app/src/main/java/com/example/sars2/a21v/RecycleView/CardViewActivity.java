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
        personName = (TextView)findViewById(R.id.prsn_description);
        personAge = (TextView)findViewById(R.id.prsn_name);
    }
}

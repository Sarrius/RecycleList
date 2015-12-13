package com.example.sars2.a21v;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mData = (TextView)findViewById(R.id.some_data);
        getData();

    }

    private void getData (){
        String s = new String(getIntent().getExtras().getString(Constants.keys.KEY_PERSON_NAME));
        mData.setText(s);

    }


}

package com.example.sars2.RecycleList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mData;
    private TextView mInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mData = (TextView)findViewById(R.id.some_person_data);
        mInfo = (TextView)findViewById(R.id.some_person_info);
        getData();

    }

    private void getData (){
        String receivedName = new String(getIntent().getExtras().getString(Constants.keys.KEY_PERSON_NAME));
        String receivedInfo = new String(getIntent().getExtras().getString(Constants.keys.KEY_PERSON_DESCRIPTION));
        mData.setText(receivedName);
        mInfo.setText(receivedInfo);

    }

}

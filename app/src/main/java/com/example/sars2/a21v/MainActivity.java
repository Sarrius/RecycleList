package com.example.sars2.a21v;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RVAdapter mAdapter;
    private EditText mPersonDescription;
    private EditText mPersonName;
    private List<Person> persons;
    private RecyclerView mRecicleView;
    private FloatingActionButton mFloatingActionButton;
    private Dialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecicleView = (RecyclerView)findViewById(R.id.rv);

        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecicleView.setLayoutManager(llm);
        mRecicleView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void alertDialog () {

        mDialog = new Dialog(MainActivity.this);
        mDialog.setTitle(R.string.add_person);
        View contentView = mDialog.getLayoutInflater().inflate(R.layout.dialog_person, null);
        mDialog.setContentView(contentView);
        mPersonName = (EditText)contentView.findViewById(R.id.personName);
        mPersonDescription = (EditText)contentView.findViewById(R.id.person_info);
        Button confirmButton = (Button)mDialog.findViewById(R.id.button_confirm);
        Button cancellButton = (Button)mDialog.findViewById(R.id.button_cancell);
        mDialog.show();

       confirmButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               persons.add(new Person(
                       mPersonName.getText().toString(),
                       mPersonDescription.getText().toString()));
               mDialog.dismiss();
                       mAdapter.notifyDataSetChanged();

           }
       });

        cancellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    private void initializeData(){
        persons = new ArrayList<>();
    }

    private void initializeAdapter(){
        mAdapter = new RVAdapter(persons);
        mRecicleView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

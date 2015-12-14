package com.example.sars2.a21v;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.sars2.a21v.Database.DBHelper;
import com.example.sars2.a21v.RecycleView.RVAdapter;
import com.example.sars2.a21v.RecycleView.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mSync;
    private RVAdapter mAdapter;
    private EditText mEditTextPersonDescription;
    private EditText mEditTextPersonName;
    private List <Person> persons;
    private RecyclerView mRecycleView;
    private FloatingActionButton mFloatingActionButton;
    private Dialog mDialog;
    private DBHelper mDBHelper;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (RecyclerView)findViewById(R.id.rv);
        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(llm);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mRecycleView.getChildItemId(llm.getFocusedChild());
                Intent activityIntent = new Intent(getApplicationContext(), SecondActivity.class);
                activityIntent.putExtra(Constants.keys.KEY_PERSON_NAME, persons.get(position).getName());
                startActivity(activityIntent);
            }
        }));

        initializeData();
        initializeAdapter();
        syncData();
    }

    private void deleteTable (){
        mDBHelper.deleteTable();
    }

    private void syncData (){

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                persons.addAll(mDBHelper.getPersonsList());
                mAdapter.notifyDataSetChanged();
                return null;
            }
        };
        asyncTask.execute();
    }

    private void showDialog () {

        mDialog = new Dialog(MainActivity.this);
        mDialog.setTitle(R.string.add_person);
        View contentView = mDialog.getLayoutInflater().inflate(R.layout.dialog_person, null);
        mDialog.setContentView(contentView);
        mEditTextPersonName = (EditText)contentView.findViewById(R.id.personName);
        mEditTextPersonDescription = (EditText)contentView.findViewById(R.id.person_info);
        Button confirmButton = (Button)mDialog.findViewById(R.id.button_confirm);
        Button cancellButton = (Button)mDialog.findViewById(R.id.button_cancell);


       confirmButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               person = new Person(mEditTextPersonName.getText().toString(),mEditTextPersonDescription.getText().toString());
               persons.add(person);
               mDBHelper.addPerson(person);
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

        mDialog.show();
    }

    private void initializeData(){

        persons = new ArrayList<>();
        mDBHelper = new DBHelper(this,null);



    }

    private void initializeAdapter(){
        mAdapter = new RVAdapter(persons);
        mRecycleView.setAdapter(mAdapter);
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

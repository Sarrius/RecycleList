package com.example.sars2.RecycleList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sars2.RecycleList.Backup.DataBackupAgent;
import com.example.sars2.RecycleList.Database.DBHelper;
import com.example.sars2.RecycleList.RecycleView.RVAdapter;
import com.example.sars2.RecycleList.RecycleView.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RVAdapter mAdapter;
    private EditText mEditTextPersonDescription;
    private EditText mEditTextPersonName;
    private List <Person> persons;
    private RecyclerView mRecycleView;
    private FloatingActionButton mFloatingActionButton;
    private FloatingActionButton mTestButton;
    private FloatingActionButton mDeleteButton;
    private Dialog mDialog;
    private DBHelper mDBHelper;
    private Person person;
    private DataBackupAgent dataBackupAgent;
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBackupAgent = new DataBackupAgent();

        initUI();
        initializeData();
        initializeAdapter();
        syncData();

    }

    private void initUI (){
        testText = (TextView)findViewById(R.id.textViewTest);
        mRecycleView = (RecyclerView)findViewById(R.id.rv);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.supportsPredictiveItemAnimations();
        mRecycleView.setLayoutManager(llm);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mRecycleView.getChildItemId(llm.getFocusedChild());
                Intent activityIntent = new Intent(getApplicationContext(), SecondActivity.class);
                activityIntent.putExtra(Constants.keys.KEY_PERSON_NAME, persons.get(position).getName());
                activityIntent.putExtra(Constants.keys.KEY_PERSON_DESCRIPTION, persons.get(position).getDescription());
                startActivity(activityIntent);
            }
        }));

        mDeleteButton = (FloatingActionButton)findViewById(R.id.btn_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBHelper.deleteTable();
            }
        });
        mTestButton = (FloatingActionButton)findViewById(R.id.test_btn);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backupData();
            }
        });
        mFloatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void syncData (){

            persons.addAll(mDBHelper.getPersonsList());
            mAdapter.notifyDataSetChanged();
    }

    private void backupData () {
        dataBackupAgent = new DataBackupAgent();
        dataBackupAgent.requestBackup(this);

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
        mDBHelper = new DBHelper(this, Constants.DATABASE_FILE_NAME, null, Constants.DATABASE_VERSION_1);
    }

    private void initializeAdapter(){
        mAdapter = new RVAdapter(persons);
        mRecycleView.setAdapter(mAdapter);
    }



}

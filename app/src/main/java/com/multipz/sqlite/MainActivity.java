package com.multipz.sqlite;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mDatabaseHelper;
    SlocksListAdpter adapter;
    List<BadCategory> list;
    ListView listview;
    Button btn_bad, btn_attitude, btn_cool,btn_royal,btn_english,btn_hindi,btn_killer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        btn_bad = (Button) findViewById(R.id.btn_bad);
        btn_attitude = (Button) findViewById(R.id.btn_attitude);
        btn_cool = (Button) findViewById(R.id.btn_cool);
        btn_royal = (Button) findViewById(R.id.btn_royal);
        btn_english = (Button) findViewById(R.id.btn_english);
        btn_hindi = (Button) findViewById(R.id.btn_hindi);
        btn_killer = (Button) findViewById(R.id.btn_killer);

        mDatabaseHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()) {
            mDatabaseHelper.getReadableDatabase();
            if (mDatabaseHelper.copyDatabase(this)) {
                //Toast.makeText(this, "Copy data success", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        mDatabaseHelper = new DatabaseHelper(this);
        list = mDatabaseHelper.getListCategory();
//        slocksList = mDatabaseHelper.getCategoryWiseData("royal");
        adapter = new SlocksListAdpter(MainActivity.this, list);
        listview.setAdapter(adapter);


        btn_bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("bad");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });

        btn_attitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("attitude");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });
        btn_cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("cool");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });

        btn_royal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("royal");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });
        btn_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("english");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });


        btn_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("hindi");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });
        btn_killer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = mDatabaseHelper.getListCategory();
                list = mDatabaseHelper.getCategoryWiseData("killer");
                adapter = new SlocksListAdpter(MainActivity.this, list);
                listview.setAdapter(adapter);
            }
        });




        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BadCategory badCategory = list.get(position);
                String contain = badCategory.getContain();
                Intent intent = new Intent(MainActivity.this, StatusActivity.class);

                intent.putExtra("contain", contain);
                startActivity(intent);
            }
        });
    }

}

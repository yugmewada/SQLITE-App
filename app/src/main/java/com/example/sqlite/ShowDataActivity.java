package com.example.sqlite;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        dbHelper= new DBHelper(this);
        listView= findViewById(R.id.list_users);
        getData();
    }

    public  void getData(){
        ArrayList<UserDataHandler> list= dbHelper.getAlldata();
        CustomAdapter customAdapter= new CustomAdapter(list,this);
        /*ArrayAdapter<CustomAdapter>adapter= new ArrayAdapter<CustomAdapter>(ShowDataActivity.this,
                android.R.layout.simple_list_item_1,new CustomAdapter[]{customAdapter});*/
        listView.setAdapter(customAdapter);
    }
}
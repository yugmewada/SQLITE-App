package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText fname,lname,email,password,contact;
    Button updateButton;
    DBHelper dbHelper;
    Bundle bundle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        toolbar= findViewById(R.id.update_toolbar);
        setSupportActionBar(toolbar);

        fname= findViewById(R.id.name1);
        lname= findViewById(R.id.name2);
        email= findViewById(R.id.email);
        password= findViewById(R.id.pass);
        contact= findViewById(R.id.contactUP);
        updateButton= findViewById(R.id.update_btn);
        
        getdata();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    private void getdata() {
        bundle=getIntent().getExtras();
        fname.setText(bundle.getString("fname"));
        lname.setText(bundle.getString("lname"));
        email.setText(bundle.getString("email"));
        password.setText(bundle.getString("pass"));
        contact.setText(bundle.getString("con"));
    }

    private void updateData() {
        bundle=getIntent().getExtras();
        dbHelper= new DBHelper(UpdateActivity.this);
        dbHelper.updateData(bundle.getString("id"), fname.getText().toString(), lname.getText().toString(),
                email.getText().toString(),password.getText().toString(),contact.getText().toString());
        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(UpdateActivity.this, MainActivity.class));
        finish();
    }
}
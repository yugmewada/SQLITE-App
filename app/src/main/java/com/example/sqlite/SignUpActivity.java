package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText fname,lname,email,password,contact;
    Button addButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname= findViewById(R.id.name1_ET);
        lname= findViewById(R.id.name2_ET);
        email= findViewById(R.id.email_ET);
        password= findViewById(R.id.pass_ET);
        contact= findViewById(R.id.contact_ET);
        addButton= findViewById(R.id.signup_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty()
                        || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                        contact.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Please Provide Data", Toast.LENGTH_SHORT).show();
                }else {
                    insertData();
                    fname.setText("");
                    lname.setText("");
                    email.setText("");
                    password.setText("");
                    contact.setText("");
                    fname.requestFocus();
                }
            }
        });
    }

    private void insertData() {
        dbHelper = new DBHelper(SignUpActivity.this);
        dbHelper.addData(fname.getText().toString(),lname.getText().toString(),
                email.getText().toString(),password.getText().toString(),
                contact.getText().toString());
        Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
    }
}
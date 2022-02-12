package com.example.pasarmalam.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Retrieve extends AppCompatActivity {


    EditText profileUsername, profileEmail, profilePhoneNo, profilePassword;
    Button edit, show;
    DatabaseReference rootDatabaseref;
    FirebaseAuth fAuth;


    public String userId, username,email,phoneNo, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);


        rootDatabaseref = FirebaseDatabase.getInstance().getReference("Users");
        fAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        userId = fAuth.getCurrentUser().getUid();
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        phoneNo = getIntent().getStringExtra("phoneNo");
        password = getIntent().getStringExtra("password");

        profileUsername = (EditText) findViewById(R.id.tvUsername);
        profileEmail = (EditText) findViewById(R.id.tvEmail);
        profilePhoneNo = (EditText) findViewById(R.id.tvPhoneNo);
        profilePassword =(EditText) findViewById(R.id.tvPassword);

        edit = findViewById(R.id.btn_edit);
        //show = findViewById(R.id.btn_show);


      edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

               String username = profileUsername.getText().toString();
              String email = profileEmail.getText().toString();
              String password =profilePassword.getText().toString();
              String phoneNo = profilePhoneNo.getText().toString();

              HashMap hashMap = new HashMap();
              hashMap.put("username", username);
              hashMap.put("email", email);
              hashMap.put("password", password);
              hashMap.put("phoneNo", phoneNo);

              //Updated Data

              rootDatabaseref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                  @Override
                  public void onSuccess(Object o) {
                      Toast.makeText(Retrieve.this, "Your Data is Successfully updated", Toast.LENGTH_SHORT).show();
                      Toast.makeText(Retrieve.this, "Must Insert All Data", Toast.LENGTH_SHORT).show();
                  }
              });
          }
      });

    }

}
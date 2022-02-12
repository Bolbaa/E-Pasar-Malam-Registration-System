package com.example.pasarmalam.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Database.Menu;
import com.example.pasarmalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuForm extends AppCompatActivity {


    FirebaseDatabase database;
    private FirebaseAuth auth;
    DatabaseReference userRef;

    EditText MenuName, MenuPrice, MenuDescr;
    Button back,insert;

    ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_form);

        //Hooks

        loadingBar = new ProgressDialog(this);

        MenuName = findViewById(R.id.menuName);
        MenuPrice = findViewById(R.id.menuPrice);
        MenuDescr = findViewById(R.id.menuDescription);


        back = findViewById(R.id.backToHome);
        insert = findViewById(R.id.Insert);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("Menu");

        //save data in Firebase on button click
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              RegisterFood();

            }

        });
      back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuForm.this,SellerHomepage2.class));
            }
        });

    }

    private void RegisterFood() {

        String foodName = MenuName.getText().toString();
        String foodPrice = MenuPrice.getText().toString();
        String foodDescription = MenuDescr.getText().toString();

        Menu menu = new Menu (foodName, foodPrice, foodDescription);

        userRef.push().setValue(menu);
        Toast.makeText(MenuForm.this, "Data Inserted", Toast.LENGTH_SHORT).show();

    }

}

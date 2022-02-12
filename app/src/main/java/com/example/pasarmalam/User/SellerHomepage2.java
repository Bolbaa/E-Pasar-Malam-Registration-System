package com.example.pasarmalam.User;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Admin.GlobalVar;
import com.example.pasarmalam.Common.LoginSignup.Login1;
import com.example.pasarmalam.R;
import com.google.firebase.auth.FirebaseAuth;

public class SellerHomepage2 extends AppCompatActivity {

    TextView profile, payment, logout, menu, Uname;
    Button btn_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_homepage2);


        //Hook
        profile = findViewById(R.id.textID);
        payment = findViewById(R.id.textpay);
        logout= findViewById(R.id.textLoc);
        menu = findViewById(R.id.textMenu);

        Uname = findViewById(R.id.textName);

        Uname.setText(GlobalVar.currentUser.getUsername());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SellerHomepage2.this, Login1.class));
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerHomepage2.this, PaymentActivity.class));
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerHomepage2.this, MenuForm.class));
            }
        });
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to exit App")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    public void callEditScreen(View view) {
        startActivity(new Intent(getApplicationContext(), Retrieve.class));
    }

}
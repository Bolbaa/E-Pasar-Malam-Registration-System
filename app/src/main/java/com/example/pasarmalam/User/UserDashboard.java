package com.example.pasarmalam.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Admin.GlobalVar;
import com.example.pasarmalam.Admin.MainActivity2;
import com.example.pasarmalam.R;

public class UserDashboard extends AppCompatActivity {

    //variables

       Button btn1, btn2, btn3, btn4;

       TextView adminName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks

        btn1 = findViewById(R.id.txtEditProfile);
        btn2 = findViewById(R.id.txtPayment);
        btn3 = findViewById(R.id.txtLocations);
        btn4 = findViewById(R.id.txtMenu);

        adminName = findViewById(R.id.txtName);

        adminName.setText(GlobalVar.currentStaff.getStaffName());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent profile = new Intent(UserDashboard.this, MainActivity2.class);
               startActivity(profile);
            }
        });


    }


    //Normal Functions


}
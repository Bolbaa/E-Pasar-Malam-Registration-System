package com.example.pasarmalam.Common.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Admin.GlobalVar;
import com.example.pasarmalam.Database.UserHelperClass;
import com.example.pasarmalam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp2ndClass extends AppCompatActivity {


    //variables
    Button Login_btn, Signup_btn;
    EditText password,username;
    ProgressDialog loadingBar;


    private FirebaseAuth UserAuth;
    private DatabaseReference UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_class);



        UserAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);


        //hooks
        Login_btn = findViewById(R.id.login_btn);
        Signup_btn = findViewById(R.id.SignUp_btn);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);



        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();

                UserLogin(Username, Password);

                startActivity(new Intent(SignUp2ndClass.this, Login1.class));
            }
        });


    }

    private void UserLogin(String Username, String Password) {

        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Sign in");
            loadingBar.setMessage("Please wait... while we are checking..");
            loadingBar.show();
        }

        UserAuth.signInWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    GlobalVar.currentUser = dataSnapshot.getValue(UserHelperClass.class);
                                    Intent Uhome = new Intent(SignUp2ndClass.this, Login1.class);
                                    startActivity(Uhome);
                                    Toast.makeText(SignUp2ndClass.this, "Successfully Sign in!!!", Toast.LENGTH_SHORT).show();
                                    finish();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
                else{
                    Toast.makeText(SignUp2ndClass.this, "User not exist!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void callSignUpScreen(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }


}














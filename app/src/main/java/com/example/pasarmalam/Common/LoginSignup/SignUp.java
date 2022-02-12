package com.example.pasarmalam.Common.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Database.UserHelperClass;
import com.example.pasarmalam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {



    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private FirebaseAuth auth;

    //variables
  ImageView backBtn;
  Button next, retrieve;
  TextView titleNext;

    //Get Data Variables
    EditText fullName, username, email, phoneNo, password, country_code_picker;
    Boolean isDataValid = false;
    ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loadingBar = new ProgressDialog(this);

        //Hooks for animation
        next = findViewById(R.id.signup_next_btn);
        retrieve = findViewById(R.id.signup_login_btn);
        titleNext = findViewById(R.id.signUp_title_btn);

        //Hooks for getting data
        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        phoneNo = findViewById(R.id.signInPhoneNumber);
        password = findViewById(R.id.signup_password);
        country_code_picker = findViewById(R.id.signIncountryCode);

        //validating the data
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        rootNode = FirebaseDatabase.getInstance();



        //save data in Firebase on button click
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uEmail = email.getText().toString();
                String uPassword = password.getText().toString();
                
                SignUpUser (uEmail, uPassword);

            }

        });


    }

    private void SignUpUser(String uEmail, String uPassword) {

        if (TextUtils.isEmpty(uEmail)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(uPassword)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait, while we are register ");
            loadingBar.show();

            auth.createUserWithEmailAndPassword(uEmail, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //save to db
                        UserHelperClass helperClass = new UserHelperClass();
                        helperClass.setFullName(fullName.getText().toString());
                        helperClass.setUsername(username.getText().toString());
                        helperClass.setEmail(email.getText().toString());
                        helperClass.setPhoneNo(phoneNo.getText().toString());
                        helperClass.setPassword(password.getText().toString());

                        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(helperClass)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignUp.this, "User Register Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this, "Register Fail" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    loadingBar.dismiss();
                }
            });
        }


}

    public void callRetrieveDataScreen(View view) {
        startActivity(new Intent(getApplicationContext(), Login1.class));
    }
}



        //Storing data in Firebase
       // UserHelperClass helperClass = new UserHelperClass(Fullname, Username, Email, PhoneNumber, Password);
        //reference.child(Username).setValue(helperClass);

        /*Toast.makeText(this, "Your Account has been created successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), Login1.class);
        startActivity(intent);
        finish();*/


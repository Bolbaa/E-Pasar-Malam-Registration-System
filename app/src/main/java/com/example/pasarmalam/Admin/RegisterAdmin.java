package com.example.pasarmalam.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Database.Staff;
import com.example.pasarmalam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterAdmin extends AppCompatActivity {

    //Variables

    FirebaseDatabase db;
    private FirebaseAuth auth;
    DatabaseReference userRef;

    EditText personFullName, personUsername, personEmail, personPassword, personRetypePassword, personPhoneNumber, personCountryCode;
    Button personRegisterAccountBtn;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

       //Hooks

        loadingBar = new ProgressDialog(this);

        personFullName = findViewById(R.id.registerName);
        personUsername = findViewById(R.id.registerUsername);
        personEmail = findViewById(R.id.registerEmail);
        personPassword = findViewById(R.id.registerPassword);
        personRetypePassword = findViewById(R.id.registerRetypePassword);
        personPhoneNumber = findViewById(R.id.registerPhoneNumber);
        personCountryCode = findViewById(R.id.countryCode);

        personRegisterAccountBtn = findViewById(R.id.registerAccount);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("Staff");

        personRegisterAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = personEmail.getText().toString();
                String pass = personPassword.getText().toString();

                RegisterUser(email, pass);

            }
        });

    }

    private void RegisterUser(String email, String pass) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait, while we are register ");
            loadingBar.show();

            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //save to db
                        Staff staff = new Staff();
                        staff.setStaffEmail(personEmail.getText().toString());
                        staff.setStaffName(personFullName.getText().toString());
                        staff.setStaffPhoneNum(personPhoneNumber.getText().toString());
                        staff.setStaffPassword(personPassword.getText().toString());
                        staff.setStaffRetypePass(personRetypePassword.getText().toString());

                        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(staff)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(RegisterAdmin.this, "Staff Register Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(RegisterAdmin.this, "Register Fail" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    loadingBar.dismiss();
                }
            });
        }
    }


}
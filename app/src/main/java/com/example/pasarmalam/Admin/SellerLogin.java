package com.example.pasarmalam.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pasarmalam.Database.Staff;
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

public class SellerLogin extends AppCompatActivity {

    EditText EmailET, PasswordET;
    TextView ForgotPasswordET;
    Button login_btn;
    ProgressDialog loadingBar;



   private FirebaseAuth auth;
   private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        auth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        EmailET = findViewById(R.id.emailET);
        PasswordET = findViewById(R.id.passwordET);
        ForgotPasswordET = findViewById(R.id.forgotPasswordET);
        login_btn = findViewById(R.id.loginBtn);



       login_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            String email = EmailET.getText().toString();
            String Password = PasswordET.getText().toString();

            SignIn(email, Password);
       }
       });
    }

    private void SignIn(String email, String Password) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Sign in");
            loadingBar.setMessage("Please wait... while we are checking..");
            loadingBar.show();
        }

        auth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    loadingBar.dismiss();
                    FirebaseDatabase.getInstance().getReference("Staff")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            GlobalVar.currentStaff = snapshot.getValue(Staff.class);
                            Intent home = new Intent(SellerLogin.this, MainActivity2.class);
                            startActivity(home);
                            Toast.makeText(SellerLogin.this, "Successfully Sign in!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Toast.makeText(SellerLogin.this, "Sign in failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    
}

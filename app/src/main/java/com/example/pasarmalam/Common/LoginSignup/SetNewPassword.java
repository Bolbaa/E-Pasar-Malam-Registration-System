package com.example.pasarmalam.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pasarmalam.R;
import com.google.android.material.textfield.TextInputLayout;

public class SetNewPassword extends AppCompatActivity {

    //Variables
    private ImageView screenIcon;
    private TextView title, description;
    private TextInputLayout newPassword, confirmNewPassword;
    private Button updatePasswordbtn;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);


        //Hooks
        screenIcon = findViewById(R.id.set_new_password_icon);
        title = findViewById(R.id.set_new_password_title);
        description = findViewById(R.id.set_new_password_description);
        newPassword = findViewById(R.id.set_new_password_new_password);
        confirmNewPassword = findViewById(R.id.set_new_password_confirm_password);
        updatePasswordbtn = findViewById(R.id.set_new_password_btn);

        //Animation Hook
        animation = AnimationUtils.loadAnimation(this,R.anim.slide_animation);

        //Set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        newPassword.setAnimation(animation);
        confirmNewPassword.setAnimation(animation);
        updatePasswordbtn.setAnimation(animation);


    }

    //call Next Screen
    public void setNewPasswordBtn(View view){
        startActivity(new Intent(getApplicationContext(),ForgetPasswordSucessMessage.class));
        finish();
    }
    //call Previous Screen on Back arrow click
    public void callBackScreenFromForgetPassword(View view){
        startActivity(new Intent(getApplicationContext(),Login1.class));
        finish();
    }
}

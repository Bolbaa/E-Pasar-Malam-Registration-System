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

public class ForgetPassword extends AppCompatActivity {

    //Variables
    private ImageView screenIcon;
    private TextView title, description;
    private TextInputLayout emailTextField;
    private Button nextBtn;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //Hooks
        screenIcon = findViewById(R.id.forget_password_icon);
        title = findViewById(R.id.forget_password_title);
        description = findViewById(R.id.forget_password_description);
        emailTextField = findViewById(R.id.forget_password_get_email);
        nextBtn = findViewById(R.id.forget_password_next_btn);

        //Animation Hook
        animation = AnimationUtils.loadAnimation(this,R.anim.slide_animation);

        //Set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        emailTextField.setAnimation(animation);
        nextBtn.setAnimation(animation);

    }


    //call Next Screen
    public void callMakeSelectionScreen(View view){
        startActivity(new Intent(getApplicationContext(),MakeSelection.class));
    }
    //call Previous Screen on Back arrow click
    public void callBackScreenFromForgetPassword(View view){
        startActivity(new Intent(getApplicationContext(),Login1.class));
        finish();
    }

}
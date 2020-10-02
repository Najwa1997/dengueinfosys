package com.example.dengueinfosys;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.tasks.OnCompleteListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    Button signup, signin, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        admin = findViewById(R.id.admin);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( StartPage.this,SignupActivity.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( StartPage.this,LoginActivity.class);
                startActivity(i);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( StartPage.this,LoginAdmin.class);
                startActivity(i);
            }
        });


    }


}
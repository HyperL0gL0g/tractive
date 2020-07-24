package com.example.realtime_tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {
    private EditText email, password;
   // private FirebaseAuth mauth;
    private static String tag="emp_login";
    private Button adlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email = (EditText) findViewById(R.id.admail);
        password = (EditText) findViewById(R.id.adpass);
        Button adlogin = (Button) findViewById(R.id.adlogin);

            adlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(admin_login.this,mapactivity.class));
                }
            });


    }
}

package com.example.realtime_tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {
    private EditText email, password;
   // private FirebaseAuth mauth;
    private static String tag="emp_login";
    private Button adlogin;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email = (EditText) findViewById(R.id.admail);
        password = (EditText) findViewById(R.id.adpass);
         adlogin = (Button) findViewById(R.id.adlogin);
    mauth=FirebaseAuth.getInstance();
            adlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String admail = email.getText().toString().trim();
                    String adpass = password.getText().toString().trim();
                    mauth.signInWithEmailAndPassword(admail, adpass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(admin_login.this, " login Success", Toast.LENGTH_LONG).show();
                                        //logged in
                                        startActivity(new Intent(admin_login.this,mapactivity.class));
                                    } else {
                                        Toast.makeText(admin_login.this, "failed to login", Toast.LENGTH_LONG).show();
                                    }
                                }

                            });

                }
            });


    }
}

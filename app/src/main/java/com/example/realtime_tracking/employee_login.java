package com.example.realtime_tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class employee_login extends AppCompatActivity {
    private EditText email, password;
    private FirebaseAuth mauth;
    private static String tag="emp_login";
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        email = (EditText) findViewById(R.id.empmail);
        password = (EditText) findViewById(R.id.emppass);
      login = (Button) findViewById(R.id.login);

        mauth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                Log.i(tag,mail);
                Log.i(tag,pass);
                mauth.signInWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(employee_login.this, " login Success", Toast.LENGTH_LONG).show();
                                    //logged in
                                    startActivity(new Intent(employee_login.this, employee_profile.class));


                                } else {
                                    Toast.makeText(employee_login.this, "failed to login", Toast.LENGTH_LONG).show();
                                }
                            }

                        });
            }


        });
    }
}

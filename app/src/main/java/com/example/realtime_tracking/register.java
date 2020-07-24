package com.example.realtime_tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  static String tag="register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        Button register = (Button) findViewById(R.id.register);
        final EditText ed_email = (EditText) findViewById(R.id.ed_mail);
        final EditText ed_name = (EditText) findViewById(R.id.ed_name);
        final EditText ed_password = (EditText) findViewById(R.id.edpass);
       // final CheckBox admin_check = (CheckBox) findViewById(R.id.admin_check);




register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      //  final boolean checked= admin_check.isChecked();
        final String email= ed_email.getText().toString().trim();
        final String name= ed_name.getText().toString().trim();
        final String password= ed_password.getText().toString().trim();
        Log.i(tag,name);
        Log.i(tag,email);

        Log.d(tag,password);


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(name, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "User Registered", Toast.LENGTH_LONG).show();
                                    } else {

                                        DatabaseException e = (DatabaseException)task.getException();
                                        Log.d(tag,e.getMessage().toString());
                                        Toast.makeText(register.this, " User NOT  Registered", Toast.LENGTH_LONG).show();

                                    }

                                }
                            });
                        }

                        else
                        {
                            Toast.makeText(register.this, "Failed to  Register", Toast.LENGTH_SHORT).show();
                        }



                    }
                });//oncomplete ends here

    }
});











    }
}

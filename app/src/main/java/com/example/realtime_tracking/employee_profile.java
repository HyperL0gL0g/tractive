
package com.example.realtime_tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class employee_profile extends AppCompatActivity {
    FirebaseFirestore db ;
    private EditText name,email;
    double lat = 0.0;
    double lng = 0.0;
    private final int REQUEST_LOCATION_PERMISSION = 1;
    private Button gotoworkspace;
    String login_uid = "";
    Location loc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        db = FirebaseFirestore.getInstance();
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        gotoworkspace=(Button)findViewById(R.id.gotowork);


        check_locationPerm();
        getLocation();


        gotoworkspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_uid= FirebaseAuth.getInstance().getUid();
                String username=name.getText().toString().trim();
                String emailid=email.getText().toString().trim();
                String online= "1";
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(lng);
                data obj = new data(username, emailid, latitude, longitude,online);
                    db.collection("user-profiles").document(login_uid).set(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(employee_profile.this,"Success",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(employee_profile.this,workspace.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(employee_profile.this,"Failed",Toast.LENGTH_LONG).show();
                        }
                    });

            }
        });

    }


    public void check_locationPerm() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(getApplicationContext()).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = Double.parseDouble(String.valueOf(location.getLatitude()));
                    lng = Double.parseDouble(String.valueOf(location.getLongitude()));
                    loc=location;
                    Log.i("lat",lat+"");

                    Log.i("lng",lng+"");

                }
            }
        });
    }








}

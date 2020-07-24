package com.example.realtime_tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class mapactivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    double lat;
    private Button refresh;
    double lng;
    private Marker myMarker;
    FirebaseFirestore db;
    String uid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapactivity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void setUpMapIfNeeded() {

        if (mMap == null) {

           // Log.d("MyMap", "setUpMapIfNeeded");
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        db = FirebaseFirestore.getInstance();
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();
               // onResume();
                  startActivity(getIntent());
            }
        });
        db.collection("user-profiles").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                data p = d.toObject(data.class);
                                if (p != null) {
                                    String employee=p.getName().toString();
                                    lat = Double.parseDouble(p.getLat());
                                    lng = Double.parseDouble(p.getLng());
                                    if (p.getOnline().equals("1"))
                                    {
                                        LatLng user_location=   new LatLng(lat, lng);
                                        myMarker = mMap.addMarker(new MarkerOptions()
                                                .position(user_location)
                                                .title(employee)
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                                    }
                                    else if(p.getOnline().equals("0"))

                                    {
                                        LatLng user_location=   new LatLng(lat, lng);
                                        myMarker = mMap.addMarker(new MarkerOptions()
                                                .position(user_location).title(employee)
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                                    }
                                }


                            }
                            //online.setText("online=" + count + "");

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mapactivity.this, "error occured", Toast.LENGTH_LONG).show();

            }

            ;
        });

    }


















}

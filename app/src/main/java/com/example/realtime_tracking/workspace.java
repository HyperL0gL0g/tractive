package com.example.realtime_tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class workspace extends AppCompatActivity {
    FirebaseFirestore db;
    String uid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

    }
    @Override
    public void onResume() {

        super.onResume();
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        DocumentReference doc = db.collection("user-profiles").document(uid);
        doc.update("online","1");

    }
    @Override
    public void onPause() {
        super.onPause();
        //Log.i(tag, "onpause");
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        DocumentReference doc = db.collection("user-profiles").document(uid);
        doc.update("online","0");
    }
    //onstop
    @Override
    public void onStop() {
        super.onStop();
        db = FirebaseFirestore.getInstance();
       // Log.i(tag, "onstop");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        DocumentReference doc = db.collection("user-profiles").document(uid);
        doc.update("online","0");
    }

    //ondestroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        db = FirebaseFirestore.getInstance();

        //Log.i(tag, "ondestroy");
        DocumentReference doc = db.collection("user-profiles").document(uid);
        doc.update("online","0");

    }
}




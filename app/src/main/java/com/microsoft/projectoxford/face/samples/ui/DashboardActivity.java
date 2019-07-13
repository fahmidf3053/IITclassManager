package com.microsoft.projectoxford.face.samples.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.microsoft.projectoxford.face.samples.LivePreviewActivity;
import com.microsoft.projectoxford.face.samples.R;

public class DashboardActivity extends AppCompatActivity {

    String  name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseFirestore.getInstance()
                .collection("Teachers")
                .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(3))
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        name  = (String) documentSnapshot.get("Name");
                        Toast.makeText(DashboardActivity.this, "Welcome: "+name, Toast.LENGTH_SHORT).show();
                    }
                });


        if (getString(R.string.subscription_key).startsWith("Please")) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.add_subscription_key_tip_title))
                    .setMessage(getString(R.string.add_subscription_key_tip))
                    .setCancelable(false)
                    .show();
        }
    }

    public void detection(View view) {

    }

    public void verification(View view) {

    }

    public void grouping(View view) {

    }

    public void Routine(View view) {
        startActivity(new Intent(this, RoutineActivity.class));
    }
    public void Face_Detect_By_Video(View view) {

        startActivity(new Intent(this, LivePreviewActivity.class));
    }

    public void identification(View view) {
        Intent intent = new Intent(this, IdentificationActivity.class);
        startActivity(intent);
    }
}

package com.microsoft.projectoxford.face.samples.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.microsoft.projectoxford.face.samples.R;

import java.util.ArrayList;

public class RoutineActivity extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter = 0;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_routine);
//        listItems.add("test 1");
//        listItems.add("test 2");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Toast.makeText(this, FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(3), Toast.LENGTH_SHORT).show();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
//        db.collection("Routine")
//                .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(3))
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//               String Course=(String)documentSnapshot.get("Course");
//               String Time = (String) documentSnapshot.get("Time");
//               Toast.makeText(RoutineActivity.this,Course+"  --->"+Time , Toast.LENGTH_SHORT).show();
//
//                listItems.add(Course+"  --->"+Time);
//                        adapter.notifyDataSetChanged();
//
//            }
//        });
        listItems.add("Course/Lab  ---> Time");
        db.collection("Teachers")
                .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(3))
                .collection("Routine")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String Course = (String) document.get("Course");
                        String Lab = (String) document.get("LAB");
                        String Time = (String) document.get("Time");
//               Toast.makeText(RoutineActivity.this,Course+"  --->"+Time , Toast.LENGTH_SHORT).show();
//
                        listItems.add(Course+"/"+Lab + "  ---> " + Time);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


    }

//    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
//    public void addItems(View v) {
//        listItems.add("Clicked : "+clickCounter++);
//        adapter.notifyDataSetChanged();
//    }
}
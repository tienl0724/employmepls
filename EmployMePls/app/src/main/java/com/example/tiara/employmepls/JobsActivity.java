package com.example.tiara.employmepls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Tiara on 2018-01-13.
 */

public class JobsActivity extends AppCompatActivity {
    List<>

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        ((TextView)findViewById(R.id.page_title)).setText("Jobs");

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("jobs");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // dataSnapshot.getChildren()
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                }
//                facts = (List<String>) dataSnapshot.getValue();
//                informationManager.setFacts(facts);
//                //for ()
//                Log.d("Firebase Database", facts.size() + " facts");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Firebase Database", databaseError.getMessage());

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

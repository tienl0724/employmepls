package com.example.tiara.employmepls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Tiara on 2018-01-13.
 */

public class AccountActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);
        ((TextView)findViewById(R.id.page_title)).setText("My Account");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }

        ((TextView)findViewById(R.id.name)).setText(user.getDisplayName());
        ((TextView)findViewById(R.id.email)).setText(user.getEmail());
        ((TextView)findViewById(R.id.resume_uploaded)).setText("");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("applications");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getValue();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Account", "onCancelled "+databaseError.getMessage());
            }
        });
        ((TextView)findViewById(R.id.jobs_applied_to)).setText("");

        findViewById(R.id.home_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

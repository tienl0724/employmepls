package com.example.tiara.employmepls;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tiara on 2018-01-14.
 */

public class FeedbackActivity extends AppCompatActivity {
    private FeedbackAdapter feedbackAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ((TextView)findViewById(R.id.page_title)).setText("Feedback");

        feedbackAdapter = new FeedbackAdapter(this, R.layout.feedback_row, new ArrayList<Feedback>());
        ListView feedbackListView = (ListView)findViewById(R.id.all_feedback_listview);
        feedbackListView.setAdapter(feedbackAdapter);

        findViewById(R.id.home_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("feedback");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedbackAdapter.clear();
                Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) dataSnapshot.getValue();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (data == null || user == null){
                    return;
                }
                for (Map<String, String> applicationEntry : data.values()){
                    // if (user.getUid().equals(applicationEntry.get("userID"))){ //TODO web needs to send user id
                    Feedback feedback = new Feedback();
                    feedback.setFeedback(applicationEntry.get("additional"));
                    feedback.setComment(applicationEntry.get("comment"));
                    feedback.setCompanyName(applicationEntry.get("companyName"));
                    feedbackAdapter.add(feedback);
                    //  }
                }

                feedbackAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Feedback", "database onCancelled " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private class GetFeedBackAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            // Connect to the Firebase database
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            // Get a reference to the todoItems child items it the database
            final DatabaseReference myRef = database.getReference("applications");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    feedbackAdapter.clear();
                    List<Map<String, String>> data = (List<Map<String, String>>) dataSnapshot.getValue();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (data == null || user == null){
                        return;
                    }
                    List<Feedback> feedbacks = new ArrayList<>();
                    for (Map<String, String> applicationEntry : data){
                        if (user.getUid().equals(applicationEntry.get("userID"))){
                            Feedback feedback = new Feedback();
                            feedback.setFeedback(applicationEntry.get("feedback"));
                            feedback.setCompanyName(applicationEntry.get("jobName"));
                            feedbackAdapter.add(feedback);
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("Feedback", "database onCancelled " + databaseError.getMessage());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            feedbackAdapter.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }
    }
}

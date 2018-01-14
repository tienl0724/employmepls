package com.example.tiara.employmepls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tiara on 2018-01-13.
 */

public class JobsActivity extends AppCompatActivity {
    List<Job> jobs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        ((TextView)findViewById(R.id.page_title)).setText("Jobs");

        findViewById(R.id.home_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ListView jobsListView = (ListView)findViewById(R.id.jobs_listview);
        final ArrayAdapter jobsAdapter = new JobsAdapter(this, R.layout.job_row, jobs);
        jobsListView.setAdapter(jobsAdapter);

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("jobs");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Jobs", ""+dataSnapshot.getChildren());
                jobsAdapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Map data = ((Map)ds.getValue());
                    if (data == null){
                        continue;
                    }
                    Job job = new Job();
                    Object title = data.get("title");
                    if (title != null) {
                        job.setTitle((String)title);
                    }
                    Object description = data.get("description");
                    if (description != null) {
                        job.setDescription((String)description);
                    }
                    Object requirements = data.get("requirements");
                    if (requirements != null) {
                        job.setRequirements((String)requirements);
                    }
                    Object salary = data.get("salary");
                    if (salary != null) {
                        job.setSalary((String)salary);
                    }
                    Object startDate = data.get("startDate");
                    if (startDate != null) {
                        job.setStartDate((String)startDate);
                    }
                    Object type = data.get("type");
                    if (type != null) {
                        job.setType((String)type);
                    }
                    Object company = data.get("company");
                    if (company != null) {
                        job.setCompany((String)company);
                    }
                    Object location = data.get("location");
                    if (location != null) {
                        job.setLocation((String)location);
                    }

                    jobs.add(job);
                   // jobsAdapter.clear();
                    //jobsAdapter.addAll(jobs);

                }
                Log.d("Firebase Database", jobs.size() + " jobs");
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

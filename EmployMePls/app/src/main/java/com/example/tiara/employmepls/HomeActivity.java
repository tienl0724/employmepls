package com.example.tiara.employmepls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Tiara on 2018-01-13.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }

        ((TextView)findViewById(R.id.page_title)).setText("Home");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();//((EmployMePlsApplication)getApplication()).getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName() == null ? user.getEmail() : user.getDisplayName();
            ((TextView) findViewById(R.id.user_name)).setText(name);
        }
    }

    public void browseJobs(View view){
        Intent intent = new Intent(this, JobsActivity.class);
        startActivity(intent);
        finish();
    }

    public void myAccount(View view){
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomeActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void viewFeedback(View view){
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
        finish();
    }
}

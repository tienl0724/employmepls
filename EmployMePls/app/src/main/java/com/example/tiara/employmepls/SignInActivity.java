package com.example.tiara.employmepls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.page_title)).setText("Sign In");

        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //user is signed in
                    Log.d("Main", "onAuthStateChanged logged in: " + user.getUid());
                } else {
                    Log.d("Main", "onAuthStateChanged logged out");
                }
            }
        };

        Spinner userTypeSpinner = (Spinner)findViewById(R.id.user_type_ca);
        List<String> userTypesStrings = new ArrayList<>();
        userTypesStrings.add("Employer");
        userTypesStrings.add("Applicant");
        ArrayAdapter<String> userTypesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userTypesStrings);
        userTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypesAdapter);
//    }
//
//    @Override
//    public View onCreateView(String name, Context context, AttributeSet attrs) {
        findViewById(R.id.create_account_submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((TextView)findViewById(R.id.email_ca)).getText().toString();
                String password = ((TextView)findViewById(R.id.password_ca)).getText().toString();
                Log.d("Main", "attepting create user " + email + " " + password);
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Main", "onCreateUser onComplete: " + task.isSuccessful());
                        if (!task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((TextView)findViewById(R.id.email)).getText().toString();
                String password = ((TextView)findViewById(R.id.password)).getText().toString();
                Log.d("Main", "attempting sign in");
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Main", "onSignIn onComplete: " + task.isSuccessful());
                        if (!task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            onSignIn();
                        }
                    }
                });
            }
        });

        findViewById(R.id.create_account_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.sign_in_table_layout).setVisibility(View.INVISIBLE);
                findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
                findViewById(R.id.create_account_button).setVisibility(View.INVISIBLE);
                findViewById(R.id.create_account_table_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.create_account_submit_button).setVisibility(View.VISIBLE);
            }
        });

//
//        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null){
            auth.removeAuthStateListener(authListener);
        }
    }

    private void onSignIn(){
        FirebaseUser user = auth.getCurrentUser();
        if (user == null){
            return;
        }
        Log.d("Main", "signed in: " + user.getDisplayName() + " " + user.getEmail());

       // ((EmployMePlsApplication)getApplication()).setCurrentUser(user);

        String userName = user.getDisplayName();
        if (userName == null){
            userName = user.getEmail();
        }
        ((TextView)findViewById(R.id.user_name)).setText(userName);

        Intent intent = new Intent(this, HomeActivity.class);

        startActivity(intent);
        finish();

    }
}

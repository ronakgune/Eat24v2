package com.main.eat24;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.main.eat24.Common.Common;
import com.main.eat24.Model.User;

import org.w3c.dom.Text;

import static android.widget.Toast.LENGTH_SHORT;

public class Profile extends AppCompatActivity {

    private static final String TAG = Profile.class.getSimpleName();
    FirebaseAuth mAuth;
    //   FirebaseUser currentUser;

    FirebaseDatabase mdatabase;
    // DatabaseReference requests;
    TextView username;
    TextView useremail;
    TextView userphone;
    Button changePass;
    Button signout;
    //private Firebase mref;
    private DatabaseReference mUserDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth=FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();
        mUserDb=mdatabase.getReference("User");
        username = findViewById(R.id.uname);
        useremail= findViewById(R.id.uemail);
        userphone= findViewById(R.id.uphone);
        changePass = findViewById(R.id.view4);
        signout = findViewById(R.id.signout);

        //mref = new Firebase("https://eat24-749c3.firebaseio.com/");

        mUserDb.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username.setText(dataSnapshot.child("name").getValue(String.class));
                useremail.setText(dataSnapshot.child("email").getValue(String.class));
                userphone.setText(dataSnapshot.child("phoneNumber").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = useremail.getText().toString().trim();



                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Profile.this, "Check email to reset your password!", LENGTH_SHORT).show();
                                    startActivity(new Intent(Profile.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(Profile.this, "Fail to send reset password email!", LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, MainActivity.class));
                finish();
            }
        });



    }}
package com.main.eat24;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.main.eat24.Model.User;
import com.main.eat24.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {

    EditText edtPhone;
    EditText edtName;
    EditText edtEmail;
    EditText edtPassword;

    Button btnSignUp;

    FirebaseDatabase database;
    DatabaseReference table_use;

    private FirebaseAuth mAuth;

    public static final String TAG = SignUp.class.getSimpleName();
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        table_use = database.getReference("User");

        mAuth = FirebaseAuth.getInstance();

        edtEmail=findViewById(R.id.edtEmail);


        edtPassword=findViewById(R.id.edtPassword);


        btnSignUp=findViewById(R.id.btnSignUp);

        edtPhone=findViewById(R.id.edtPhone);

        edtName=findViewById(R.id.edtName);
    }

    @OnClick(R.id.btnSignUp)
    public void onButtonSignUpClick(View view) {

        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please waiting...");
        mDialog.show();
        email=edtEmail.getText().toString();
        password=edtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sign up Successful",Toast.LENGTH_LONG).show();
                    table_use.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("name").setValue(edtName.getText().toString());
                            dataSnapshot.getRef().child("phoneNumber").setValue(edtPhone.getText().toString());
                            dataSnapshot.getRef().child("email").setValue(edtEmail.getText().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    finish();
                    startActivity(new Intent(SignUp.this,home.class));
                }
            }
        });


           /* table_use.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //check if already
                    if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                        mDialog.dismiss();
                        Toast.makeText(SignUp.this, "Phone number already register", Toast.LENGTH_SHORT).show();
                    } else {
                        mDialog.dismiss();
                        User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                        table_use.child(edtPhone.getText().toString()).setValue(user);
                        Toast.makeText(SignUp.this, "Sign up successfully!!!", Toast.LENGTH_SHORT).show();
                        finish();
                    }




                }

//                private void updateUI(FirebaseUser user) {
//                    hideProgressDialog();
//                    if (user != null) {
//                        mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
//                        mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//                        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//                        findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
//                    } else {
//                        mStatusTextView.setText(R.string.signed_out);
//                        mDetailTextView.setText(null);
//
//                        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//                        findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
//                    }
//                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
*/


    }


}



//---------------------------------------------------------------------------







//package com.main.eat24;
//
//import android.app.ProgressDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.main.eat24.Model.User;
//import com.main.eat24.R;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class SignUp extends AppCompatActivity {
//
//    @BindView(R.id.edtPhone)
//    EditText edtPhone;
//
//    @BindView(R.id.edtName)
//    EditText edtName;
//
//    @BindView(R.id.edtPassword)
//    EditText edtPassword;
//
//    @BindView(R.id.btnSignUp)
//    Button btnSignUp;
//
//    FirebaseDatabase database;
//    DatabaseReference table_use;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        ButterKnife.bind(this);
//
//        database = FirebaseDatabase.getInstance();
//        table_use = database.getReference("User");
//    }
//
//    @OnClick(R.id.btnSignUp)
//    public void onButtonSignUpClick(View view) {
//
//        final ProgressDialog mDialog = new ProgressDialog(this);
//        mDialog.setMessage("Please waiting...");
//        mDialog.show();
//
//        table_use.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //check if already
//                if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
//                    mDialog.dismiss();
//                    Toast.makeText(SignUp.this, "Phone number already register", Toast.LENGTH_SHORT).show();
//                } else {
//                    mDialog.dismiss();
//                    User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
//                    table_use.child(edtPhone.getText().toString()).setValue(user);
//                    Toast.makeText(SignUp.this, "Sign up successfully!!!", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//}

package com.main.eat24;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.main.eat24.Common.Common;
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

import static android.widget.Toast.LENGTH_SHORT;
import static com.main.eat24.Common.Common.currentUserPhone;

public class SignIn extends AppCompatActivity {

    EditText edtEmail;

    EditText edtPassword;

    TextView forgotPass;
    FirebaseAuth mAuth;


    Button btnSignInActivity;

    FirebaseDatabase database;
    DatabaseReference table_use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);

        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);

        forgotPass = findViewById(R.id.forgot);

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString().trim();

                if (email.matches("")) {
                    Toast.makeText(SignIn.this, "You did not enter an Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignIn.this, "Check email to reset your password!", LENGTH_SHORT).show();
                                        startActivity(new Intent(SignIn.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(SignIn.this, "Fail to send reset password email!", LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        //init firebase
        mAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        table_use = database.getReference("User");
    }

    @OnClick(R.id.btnSignInActivity)
    public void onButtonSignInClick(View view) {

        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait ...");
        mDialog.show();

        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error in sign in",Toast.LENGTH_LONG).show();
                }else{

                    finish();
                    startActivity(new Intent(SignIn.this,home.class));
                }
            }
        });



    }
}








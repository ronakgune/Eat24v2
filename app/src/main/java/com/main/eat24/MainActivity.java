package com.main.eat24;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.main.eat24.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnSignIn)
    Button btnSignIn;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @BindView(R.id.txtSlogan)
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        txtSlogan.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @OnClick(R.id.btnSignIn)
    public void onButtonSignInClick(View view) {
        Intent signIn = new Intent(MainActivity.this, SignIn.class);
        startActivity(signIn);
    }

    @OnClick(R.id.btnSignUp)
    public void onButtonSignUpClick(View view) {
        Intent signUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(signUp);
    }
}

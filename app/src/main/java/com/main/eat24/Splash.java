package com.main.eat24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
//import com.main.eat24.ViewHolder.R;

public class Splash extends AppCompatActivity {

    //private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
       // tv.startAnimation(myanim);
        iv.startAnimation(myanim);
        final Intent i = new Intent(this,IntroActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);

                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    if(auth.getCurrentUser() == null){

                        Intent intent = new Intent(Splash.this, IntroActivity.class);

                        startActivity(intent);

                        finish();

                    }

                    else{

                        Intent intent = new Intent(Splash.this, home.class);

                        startActivity(intent);

                        finish();

                    }
//                    startActivity(i);
//                    finish();
                }
            }
        }; timer.start();
    }
}

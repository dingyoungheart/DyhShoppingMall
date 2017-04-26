package dyhshoppingmall.dyhshoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import dyhshoppingmall.dyhshoppingmall.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }
        }, 3000);

    }
}

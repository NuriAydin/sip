package com.nuri.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class uploadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);

        new uploadingActivity.SplashThread().start();

    }
    public class SplashThread extends Thread{
        @Override
        public void run()
        {
            try{
                Thread.sleep(1000);
                Intent intentchat = new Intent(uploadingActivity.this,chatActivity.class);
                intentchat.putExtra("info","gec");
                startActivity(intentchat);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.nuri.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class yukleniyorActivity extends AppCompatActivity {

   /*TextView textView;
    ProgressBar progressBar;
    String kayitli_tc;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yukleniyor);

       /*textView = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar2);

        new yukleniyorActivity.SplashThread().start();

        Intent intent4 = getIntent();
        kayitli_tc = intent4.getStringExtra("kayitli_tc");
        String info = intent4.getStringExtra("info");

*/
    }/*
    public class SplashThread extends Thread{
        @Override
        public void run()
        {
            try{
                Thread.sleep(1000);
                Intent intentchat = new Intent(yukleniyorActivity.this,chatActivity.class);
                intentchat.putExtra("kayitli_tc",kayitli_tc);
                intentchat.putExtra("info","gec");
                startActivity(intentchat);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}

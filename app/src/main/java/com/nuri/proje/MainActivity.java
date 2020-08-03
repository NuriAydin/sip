package com.nuri.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SplashThread().start();
    }

    public class SplashThread extends Thread{
        @Override
        public void run()
        {
            try{
                Thread.sleep(2500);
                Intent intent = new Intent(MainActivity.this,giris_kayitActivity.class);
                startActivity(intent);
            }
            catch (Exception e) {
                e.printStackTrace();
        }
        }
    }
}

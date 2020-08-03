package com.nuri.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class kayitActivity extends AppCompatActivity {

    //private Button button2;
    //private EditText editText2;
    //private EditText editText;
    //private EditText editText6;
    //private Button button3;
    String aktarilan_fakt,aktarilan_sinif,aktarilan_bolum;
    String kullanici_adi;
    //String tc,password;

    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        //firebaseAuth = FirebaseAuth.getInstance();


        //final Button button2 = (Button) findViewById(R.id.button2);
        //Button button3 = (Button) findViewById(R.id.button3);
        //editText2 = findViewById(R.id.editText2);
        //final EditText editText = (EditText) findViewById(R.id.editText);
        //editText6 = findViewById(R.id.editText6);

       /* Intent intent4 = getIntent();
        //String kayitli_tc = intent4.getStringExtra("kayitli_tc");
        String info = intent4.getStringExtra("info");

        Intent gecisintent = getIntent();
        aktarilan_fakt = gecisintent.getStringExtra("faktbilgi");
        aktarilan_sinif = gecisintent.getStringExtra("sinifbilgi");
        aktarilan_bolum = gecisintent.getStringExtra("bolumbilgi");

        if (info.matches("gec"))
        {
            Intent intent55 = new Intent(kayitActivity.this, chatActivity.class);
            //intent55.putExtra("kayitli_tc", kayitli_tc);
            intent55.putExtra("info", "gec");
            startActivity(intent55);
        }
        else
            {
            /*button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(kayitActivity.this, sifremi_unuttumActivity.class);
                    startActivity(intent2);
                }
            });*/

        /*button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullanici_adi = editText2.getText().toString();
                //tc = editText6.getText().toString();
                //password = editText.getText().toString();


                //                if (kullanici_adi.matches("") || tc.matches("")) {
                if (kullanici_adi.matches("")) {
                    Toast.makeText(kayitActivity.this, "Alanı boş bırakmayınız", Toast.LENGTH_LONG).show();
                }
                else
                    {
                    //System.out.println(kullanici_adi);
                    //System.out.println(tc);
                        Intent intent3 = new Intent(kayitActivity.this, chatActivity.class);
                        intent3.putExtra("faktbilgi", aktarilan_fakt);
                        intent3.putExtra("sinifbilgi", aktarilan_sinif);
                        intent3.putExtra("bolumbilgi", aktarilan_bolum);
                        intent3.putExtra("kullanici_adi", kullanici_adi);
                        intent3.putExtra("info","devam");
                        //intent3.putExtra("tc", tc);
                        startActivity(intent3);
                        finish();


                    }
            }
        });*/
    }
    }
//}


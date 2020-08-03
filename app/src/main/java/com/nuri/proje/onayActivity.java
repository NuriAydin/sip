package com.nuri.proje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class onayActivity extends AppCompatActivity {


    String aktarilan_fakt;
    String aktarilan_sinif;
    String aktarilan_bolum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onay);

        Intent intent22 = getIntent();
        //String kayitli_tc = intent22.getStringExtra("kayitli_tc");
        String info = intent22.getStringExtra("info");
        System.out.println(info);

        if (info.matches("gec")) {
            Intent intent4 = new Intent(onayActivity.this, chatActivity.class);
            //intent4.putExtra("kayitli_tc", kayitli_tc);
            intent4.putExtra("info", "gec");
            startActivity(intent4);
        }
        else{
            TextView aktarilan_bilgi = (TextView) findViewById(R.id.aktarilan_bilgi);
        Intent intent1 = getIntent();
        aktarilan_fakt = intent1.getStringExtra("faktbilgi");
            System.out.println(aktarilan_fakt);
        aktarilan_sinif = intent1.getStringExtra("sinifbilgi");
        aktarilan_bolum = intent1.getStringExtra("bolumbilgi");
        aktarilan_bilgi.setText("Fakülte : \n" + aktarilan_fakt + "\n" + "\nBölüm : \n" + aktarilan_bolum
                + "\n" + "\nSınıf : \n" + aktarilan_sinif);

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisintent = new Intent(onayActivity.this, chatActivity.class);
                gecisintent.putExtra("faktbilgi", aktarilan_fakt);
                gecisintent.putExtra("sinifbilgi", aktarilan_sinif);
                gecisintent.putExtra("bolumbilgi", aktarilan_bolum);
                gecisintent.putExtra("info","devam");
                gecisintent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(gecisintent);
                finish();
            }
        });

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backintent = new Intent(onayActivity.this, secimActivity.class);
                backintent.putExtra("info","devam");
                startActivity(backintent);
                finish();
            }
        });
    }
    }
    @Override
    public void onBackPressed() {

        backButtonHandler();
        return;
    }
    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                onayActivity.this);
        alertDialog.setTitle("Uygulamadan ayrılıyorsun");
        alertDialog.setMessage("Uygulamadan çıkmak istediğine emin misin ? ");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setPositiveButton("EVET",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        // System.exit(0);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("HAYIR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}

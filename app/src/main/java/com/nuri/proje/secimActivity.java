package com.nuri.proje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class secimActivity extends AppCompatActivity {

    Spinner fakulteler, bolumler, siniflar;

    ArrayList<String> arrayList_fakulteler;
    ArrayAdapter<String> arrayAdapter_fakulteler;

    ArrayList<String> arrayList_muhfakt, arrayList_feffakt, arrayList_gzlsntfakt, arrayList_islmfakt, arrayList_sglkfakt,
            arrayList_uygfakt, arrayList_zirtdogafakt, arraylist_ibffakt;
    ArrayAdapter<String> arrayAdapter_bolumler;

    ArrayList<String> arrayList_siniflar;
    ArrayAdapter<String> arrayAdapter_siniflar;

    String fakt_bilgi, sinif_bilgi, bolum_bilgi;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String MAIN_DATA="com.nuri.proje.MAIN_DATA";
    private String BOLUM_INFO="com.nuri.proje.BOLUM_INFO";
    private String SINIF_INFO="com.nuri.proje.SINIF_INFO";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);

        //Intent intent11 = getIntent();
        //String kayitli_tc = intent11.getStringExtra("kayitli_tc");
        //String info = intent11.getStringExtra("info");
        //System.out.println(info);


        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        System.out.println(info);

        Intent backintent = getIntent();
        info = backintent.getStringExtra("info");



        if (info.matches("gec")) {
            Intent intent22 = new Intent(secimActivity.this, onayActivity.class);
            //intent22.putExtra("kayitli_tc", kayitli_tc);
            intent22.putExtra("info", "gec");
            startActivity(intent22); }
        else{

            final Spinner fakulteler = (Spinner) findViewById(R.id.fakultelerr);
            final Spinner bolumler = (Spinner) findViewById(R.id.bolumlerr);
            final Spinner siniflar = (Spinner) findViewById(R.id.siniflarr);

            arrayList_fakulteler = new ArrayList<>();
            arrayList_fakulteler.add("Mühendislik Fakültesi*");
            arrayList_fakulteler.add("Fen-Edebiyat Fakültesi");
            arrayList_fakulteler.add("Güzel Sanatlar ve Tasarım Fakültesi");
            arrayList_fakulteler.add("İslami İlimler Fakültesi");
            arrayList_fakulteler.add("Sağlık Bilimleri Fakültesi*");
            arrayList_fakulteler.add("Uygulamalı Bilimler Fakültesi");
            arrayList_fakulteler.add("Ziraat ve Doğa Bilimleri Fakültesi");
            arrayList_fakulteler.add("İktisadi ve İdari Bilimler Fakültesi");

            arrayAdapter_fakulteler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                    arrayList_fakulteler);
            fakulteler.setAdapter(arrayAdapter_fakulteler);

            arrayList_siniflar = new ArrayList<>();
            arrayList_siniflar.add("1*");
            arrayList_siniflar.add("2");
            arrayList_siniflar.add("3");
            arrayList_siniflar.add("4");
            arrayList_siniflar.add("Diğer");
            arrayAdapter_siniflar = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                    arrayList_siniflar);
            siniflar.setAdapter(arrayAdapter_siniflar);

            siniflar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sinif_bilgi = siniflar.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            arrayList_muhfakt = new ArrayList<>();
            arrayList_muhfakt.add("Bilgisayar Mühendisliği*");
            arrayList_muhfakt.add("Elektrik-Elektronik Mühendisliği");
            arrayList_muhfakt.add("Endüstri Mühendisliği");
            arrayList_muhfakt.add("İnşaat Mühendisliği");
            arrayList_muhfakt.add("Kimya Mühendisliği");
            arrayList_muhfakt.add("Makina Mühendisliği");
            arrayList_muhfakt.add("Yazılım Mühendisliği");

            arrayList_feffakt = new ArrayList<>();
            arrayList_feffakt.add("Arkeoloji");
            arrayList_feffakt.add("Coğrafya");

            arrayList_gzlsntfakt = new ArrayList<>();
            arrayList_gzlsntfakt.add("Endüstri Ürün Tasarımı");
            arrayList_gzlsntfakt.add("Resim");

            arrayList_islmfakt = new ArrayList<>();
            arrayList_islmfakt.add("Temel İslam Bilimleri");
            arrayList_islmfakt.add("Felsefe ve Din Bilimleri");

            arrayList_sglkfakt = new ArrayList<>();
            arrayList_sglkfakt.add("Hemşirelik*");
            arrayList_sglkfakt.add("Çocuk Gelişimi");

            arrayList_uygfakt = new ArrayList<>();
            arrayList_uygfakt.add("Bankacılık ve Finans");
            arrayList_uygfakt.add("Muhasebe ve Denetim");

            arrayList_zirtdogafakt = new ArrayList<>();
            arrayList_zirtdogafakt.add("Bahçe Bitkileri");
            arrayList_zirtdogafakt.add("Bitki Koruma");

            arraylist_ibffakt = new ArrayList<>();
            arraylist_ibffakt.add("İşletme");
            arraylist_ibffakt.add("Maliye");

            fakulteler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_muhfakt);
                    }
                    if (position == 1) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_feffakt);
                    }
                    if (position == 2) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_gzlsntfakt);
                    }
                    if (position == 3) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_islmfakt);
                    }
                    if (position == 4) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_sglkfakt);
                    }
                    if (position == 5) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_uygfakt);
                    }
                    if (position == 6) {
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arrayList_zirtdogafakt);
                    }
                    if (position == 7){
                        arrayAdapter_bolumler = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                                arraylist_ibffakt);
                    }
                    bolumler.setAdapter(arrayAdapter_bolumler);

                    bolumler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            bolum_bilgi = bolumler.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    fakt_bilgi = fakulteler.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            sharedPreferences = getSharedPreferences(MAIN_DATA,MODE_PRIVATE);
            editor=sharedPreferences.edit();


            Button button = (Button) findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString(BOLUM_INFO,bolum_bilgi);
                    editor.putString(SINIF_INFO,sinif_bilgi);
                    editor.commit();

                    Intent intent1 = new Intent(secimActivity.this, onayActivity.class);
                    intent1.putExtra("faktbilgi", fakt_bilgi);
                    intent1.putExtra("sinifbilgi", sinif_bilgi);
                    intent1.putExtra("bolumbilgi", bolum_bilgi);
                    intent1.putExtra("info","devam");
                    startActivity(intent1);
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
                secimActivity.this);
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


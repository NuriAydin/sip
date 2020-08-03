package com.nuri.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class kullanicilarActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> kullanici_adi_list;
    ArrayList<String> fakt_list;
    ArrayList<String> sinif_list;
    ArrayList<String> bolum_list;
    //ArrayList<String> tc_list;
    ArrayList<Integer> id_list;

    //hemşirelik
    ListView listView1;
    ArrayList<String> kullanici_adi_list1;
    ArrayList<String> fakt_list1;
    ArrayList<String> sinif_list1;
    ArrayList<String> bolum_list1;
    //ArrayList<String> tc_list;
    ArrayList<Integer> id_list1;

    //diger
    ListView listView2;
    ArrayList<String> kullanici_adi_list2;
    ArrayList<String> fakt_list2;
    ArrayList<String> sinif_list2;
    ArrayList<String> bolum_list2;
    //ArrayList<String> tc_list;
    ArrayList<Integer> id_list2;

    ArrayAdapter arrayAdapter;

    //hemşirelik
    ArrayAdapter arrayAdapter1;

    //diger
    ArrayAdapter arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanicilar);

        listView = findViewById(R.id.listView);
        //hemşirelik
        listView1 = findViewById(R.id.listView);
        //
        listView2 = findViewById(R.id.listView);

        kullanici_adi_list = new ArrayList<String>();
        fakt_list= new ArrayList<String>();
        sinif_list = new ArrayList<String>();
        bolum_list = new ArrayList<String>();
        //tc_list = new ArrayList<String>();
        id_list = new ArrayList<Integer>();

        //hemşirelik
        kullanici_adi_list1 = new ArrayList<String>();
        fakt_list1= new ArrayList<String>();
        sinif_list1 = new ArrayList<String>();
        bolum_list1 = new ArrayList<String>();
        //tc_list = new ArrayList<String>();
        id_list1 = new ArrayList<Integer>();

        //diger
        kullanici_adi_list2 = new ArrayList<String>();
        fakt_list2= new ArrayList<String>();
        sinif_list2 = new ArrayList<String>();
        bolum_list2 = new ArrayList<String>();
        //tc_list = new ArrayList<String>();
        id_list2 = new ArrayList<Integer>();

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,kullanici_adi_list);
        listView.setAdapter(arrayAdapter);

        //hemşirelik
        arrayAdapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,kullanici_adi_list1);
        listView.setAdapter(arrayAdapter1);

        //diger
        arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,kullanici_adi_list2);
        listView.setAdapter(arrayAdapter1);


        getData();
    }

    public void getData(){


        try{
            SQLiteDatabase database = this.openOrCreateDatabase("müh1bilgisayar", MODE_PRIVATE, null);
            //hemşirelik
            SQLiteDatabase database1 = this.openOrCreateDatabase("sglk1hemsire", MODE_PRIVATE, null);
            //diger
            SQLiteDatabase database2 = this.openOrCreateDatabase("diger", MODE_PRIVATE, null);


            Cursor cursor = database.rawQuery("SELECT * FROM müh1bilgisayar", null);
            //hemşirelik
            Cursor cursor1 = database1.rawQuery("SELECT * FROM sglk1hemsire", null);
            //diger
            Cursor cursor2 = database1.rawQuery("SELECT * FROM diger", null);


            int idIx = cursor.getColumnIndex("id");
            int kullanici_adiIx = cursor.getColumnIndex("kullanici_adi");
            int faktIx = cursor.getColumnIndex("fakt");
            int sinifIx = cursor.getColumnIndex("sinif");
            int bolumIx = cursor.getColumnIndex("bolum");
            //int tcIx= cursor.getColumnIndex("tc");

            //hemşirelik
            int idIx1 = cursor1.getColumnIndex("id");
            int kullanici_adiIx1 = cursor1.getColumnIndex("kullanici_adi");
            int faktIx1 = cursor1.getColumnIndex("fakt");
            int sinifIx1 = cursor1.getColumnIndex("sinif");
            int bolumIx1 = cursor1.getColumnIndex("bolum");

            //diger
            int idIx2 = cursor2.getColumnIndex("id");
            int kullanici_adiIx2 = cursor2.getColumnIndex("kullanici_adi");
            int faktIx2 = cursor2.getColumnIndex("fakt");
            int sinifIx2 = cursor2.getColumnIndex("sinif");
            int bolumIx2 = cursor2.getColumnIndex("bolum");


            while(cursor.moveToNext()){

                kullanici_adi_list.add(cursor.getString(kullanici_adiIx));
                fakt_list.add(cursor.getString(faktIx));
                sinif_list.add(cursor.getString(sinifIx));
                bolum_list.add(cursor.getString(bolumIx));
                //tc_list.add(cursor.getString(tcIx));
                id_list.add(cursor.getInt(idIx));

                System.out.println("Ad Soyad : "+kullanici_adi_list);
                System.out.println("Fakülte : "+fakt_list);
                System.out.println("Sınıf : "+sinif_list);
                System.out.println("Bölüm : "+bolum_list);
                //System.out.println("TC : "+tc_list);
                System.out.println("id : "+id_list);
            }arrayAdapter.notifyDataSetChanged();
            cursor.close();

            //hemşirelik
            while(cursor1.moveToNext()){

                kullanici_adi_list1.add(cursor1.getString(kullanici_adiIx1));
                fakt_list1.add(cursor1.getString(faktIx1));
                sinif_list1.add(cursor1.getString(sinifIx1));
                bolum_list1.add(cursor1.getString(bolumIx1));
                //tc_list.add(cursor.getString(tcIx));
                id_list1.add(cursor1.getInt(idIx1));

                System.out.println("Ad Soyad : "+kullanici_adi_list1);
                System.out.println("Fakülte : "+fakt_list1);
                System.out.println("Sınıf : "+sinif_list1);
                System.out.println("Bölüm : "+bolum_list1);
                //System.out.println("TC : "+tc_list);
                System.out.println("id : "+id_list1);
            }
            arrayAdapter1.notifyDataSetChanged();
            cursor1.close();

            //diger
            while(cursor2.moveToNext()){

                kullanici_adi_list2.add(cursor1.getString(kullanici_adiIx2));
                fakt_list2.add(cursor1.getString(faktIx2));
                sinif_list2.add(cursor1.getString(sinifIx2));
                bolum_list2.add(cursor1.getString(bolumIx2));
                //tc_list.add(cursor.getString(tcIx));
                id_list2.add(cursor1.getInt(idIx2));

                System.out.println("Ad Soyad : "+kullanici_adi_list2);
                System.out.println("Fakülte : "+fakt_list2);
                System.out.println("Sınıf : "+sinif_list2);
                System.out.println("Bölüm : "+bolum_list2);
                //System.out.println("TC : "+tc_list);
                System.out.println("id : "+id_list2);
            }
            arrayAdapter2.notifyDataSetChanged();
            cursor1.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}



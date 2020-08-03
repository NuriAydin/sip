package com.nuri.proje;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class chatActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private FirebaseFirestore firebaseFirestore2;//hemşirelik için

    String aktarilan_fakt,aktarilan_sinif,aktarilan_bolum;
    //String aktarilan_mail,aktarilan_tc;
    //String aktarilan_kullanici_adi;

    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;
    FeedRecycler feedRecycler;
    FeedRecycler feedRecycler2;//hemşirelik için

    Context context = this;

    private String MAIN_DATA="com.nuri.proje.MAIN_DATA";
    private String BOLUM_INFO="com.nuri.proje.BOLUM_INFO";
    private String SINIF_INFO="com.nuri.proje.SINIF_INFO";
    String bolum_bilgisi,sinif_bilgisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        bolum_bilgisi=getSharedPreferences(MAIN_DATA,MODE_PRIVATE).getString(BOLUM_INFO,"Bölüm bilgisi bulunamadı.");
        sinif_bilgisi=getSharedPreferences(MAIN_DATA,MODE_PRIVATE).getString(SINIF_INFO,"Sınıf bilgisi bulunamadı.");
        System.out.println(bolum_bilgisi+"SAKLANAN VERİ");
        System.out.println(sinif_bilgisi+"SAKLANAN VERİ");


        userEmailFromFB = new ArrayList<>();
        userCommentFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        firebaseFirestore2 = FirebaseFirestore.getInstance();

        //getDataFromFirestore();

        //Intent intent4 = getIntent();
        //String kayitli_tc = intent5.getStringExtra("kayitli_tc");
        //String info = intent4.getStringExtra("info");
        //System.out.println(info+"chat");
        //System.out.println(kayitli_tc);

        Intent gecisintent = getIntent();
        aktarilan_fakt = gecisintent.getStringExtra("faktbilgi");
        aktarilan_sinif = gecisintent.getStringExtra("sinifbilgi");
        aktarilan_bolum = gecisintent.getStringExtra("bolumbilgi");
        //aktarilan_kullanici_adi = gecisintent.getStringExtra("kullanici_adi");
        //aktarilan_mail = intent3.getStringExtra("kullanici_adi");
        //aktarilan_tc = intent3.getStringExtra("tc");

        System.out.println(aktarilan_fakt);
        System.out.println(aktarilan_sinif);
        System.out.println(aktarilan_bolum);
        //System.out.println(aktarilan_kullanici_adi);
        //System.out.println(aktarilan_tc);
        //System.out.println(aktarilan_fakt);


        Intent intentchat = getIntent();
        String info = intentchat.getStringExtra("info");

        if (info.matches("gec"))

        {
            if (bolum_bilgisi.equals("Bilgisayar Mühendisliği*") && sinif_bilgisi.equals("1*"))
            {
            getDataFromFirestore();// eğer müh seçildiyse datayı çağırıyo ve chat'i açıyo !!!!!!!!!!!!!
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            feedRecycler = new FeedRecycler(userEmailFromFB,userCommentFromFB,userImageFromFB);
            recyclerView.setAdapter(feedRecycler);
            }
            else if (bolum_bilgisi.equals("Hemşirelik*") && sinif_bilgisi.equals("1*"))
            {
                getDataFromFirestore2();//hemşirelik için çağrılıyor
                RecyclerView recyclerView2 = findViewById(R.id.recyclerView);
                recyclerView2.setLayoutManager(new LinearLayoutManager(this));
                feedRecycler2 = new FeedRecycler(userEmailFromFB,userCommentFromFB,userImageFromFB);
                recyclerView2.setAdapter(feedRecycler2);

            }
        }

        else
            {
            if (aktarilan_fakt.equals("Mühendislik Fakültesi*") && aktarilan_sinif.equals("1*")
                    && aktarilan_bolum.equals("Bilgisayar Mühendisliği*")) {
                System.out.println("oke");

                //her veritabanı denemesinde uygulamayı emülatörden silde dene!!!!!!!!!!!!!!!!!!!!!!1

                try {
                    SQLiteDatabase database = this.openOrCreateDatabase("müh1bilgisayar", MODE_PRIVATE, null);

                    database.execSQL("CREATE TABLE IF NOT EXISTS müh1bilgisayar(id INTEGER PRIMARY KEY," +
                            "fakt VARCHAR,sinif VARCHAR,bolum VARCHAR)");

                    String sqlString = "INSERT INTO müh1bilgisayar(fakt , sinif , bolum) VALUES(?,?,?)";

                    SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                    //sqLiteStatement.bindString(1, aktarilan_kullanici_adi);
                    sqLiteStatement.bindString(1, aktarilan_fakt);
                    sqLiteStatement.bindString(2, aktarilan_sinif);
                    sqLiteStatement.bindString(3, aktarilan_bolum);
                    sqLiteStatement.execute();

                    System.out.println("okey");

                    //!!!!!!!!!!!!!!!!!!!!!!!!!!
                    getDataFromFirestore();// eğer müh seçildiyse datayı çağırıyo ve chat'i açıyo !!!!!!!!!!!!!
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    feedRecycler = new FeedRecycler(userEmailFromFB,userCommentFromFB,userImageFromFB);
                    recyclerView.setAdapter(feedRecycler);

                    //!!!!!!!!!!!!!!!!!!!!!!!!!!
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if(aktarilan_fakt.equals("Sağlık Bilimleri Fakültesi*") && aktarilan_bolum.equals("Hemşirelik*") &&
            aktarilan_sinif.equals("1*")) {
                try{
                SQLiteDatabase database1 = this.openOrCreateDatabase("sglk1hemsire", MODE_PRIVATE, null);

                database1.execSQL("CREATE TABLE IF NOT EXISTS sglk1hemsire(id INTEGER PRIMARY KEY," +
                        "fakt VARCHAR,sinif VARCHAR,bolum VARCHAR)");

                String sqlString = "INSERT INTO sglk1hemsire(fakt , sinif , bolum) VALUES(?,?,?)";

                SQLiteStatement sqLiteStatement = database1.compileStatement(sqlString);
                //sqLiteStatement.bindString(1, aktarilan_kullanici_adi);
                sqLiteStatement.bindString(1, aktarilan_fakt);
                sqLiteStatement.bindString(2, aktarilan_sinif);
                sqLiteStatement.bindString(3, aktarilan_bolum);
                sqLiteStatement.execute();

                //!!!!!!!!!!!!!!!!!!!!!!!!!!
                /*getDataFromFirestore();// eğer hemsire seçildiyse datayı çağırıyo ve chat'i açıyo !!!!!!!!!!!!!
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                feedRecycler = new FeedRecycler(userEmailFromFB, userCommentFromFB, userImageFromFB);
                recyclerView.setAdapter(feedRecycler);*/
                //!!!!!!!!!!!!!!!!!!!!!!!!!!

                    getDataFromFirestore2();//hemşirelik için çağrılıyor

            }catch (Exception e){
                    e.printStackTrace();
                }
            }

            else{
                Toast.makeText(chatActivity.this, "Sınıfın için en kısa sürede aktif olacak...", Toast.LENGTH_LONG).show();
                try{
                    SQLiteDatabase database2 = this.openOrCreateDatabase("diger", MODE_PRIVATE, null);
                    database2.execSQL("CREATE TABLE IF NOT EXISTS sglk1hemsire(id INTEGER PRIMARY KEY," +
                            "fakt VARCHAR,sinif VARCHAR,bolum VARCHAR)");
                    String sqlString = "INSERT INTO diger(fakt , sinif , bolum) VALUES(?,?,?,?)";
                    SQLiteStatement sqLiteStatement = database2.compileStatement(sqlString);
                    //sqLiteStatement.bindString(1, aktarilan_kullanici_adi);
                    sqLiteStatement.bindString(1, aktarilan_fakt);
                    sqLiteStatement.bindString(2, aktarilan_sinif);
                    sqLiteStatement.bindString(3, aktarilan_bolum);
                    sqLiteStatement.execute();

                }catch (Exception e){e.printStackTrace();}
            }

        }

    }

    @Override
    public void onBackPressed() {

        backButtonHandler();
        return;
    }
    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                chatActivity.this);
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

    public void getDataFromFirestore(){

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null){
                    Toast.makeText(chatActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null){
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){

                        Map<String , Object> data = snapshot.getData();

                        String comment = (String) data.get("comment");
                        String userEmail = (String) data.get("useremail");
                        String downloadUrl = (String) data.get("downloadurl");

                        //System.out.println(comment);

                        userEmailFromFB.add(userEmail);
                        userCommentFromFB.add(comment);
                        userImageFromFB.add(downloadUrl);

                        feedRecycler.notifyDataSetChanged();

                    }
                }

            }
        });

    }

    public void getDataFromFirestore2(){

        CollectionReference collectionReference2 = firebaseFirestore2.collection("Posts2");

        collectionReference2.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots2, @Nullable FirebaseFirestoreException e) {

                if (e != null){
                    Toast.makeText(chatActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
                if (queryDocumentSnapshots2 != null)
                {
                    for (DocumentSnapshot snapshot2 : queryDocumentSnapshots2.getDocuments())
                    {
                        Map<String , Object> data2 = snapshot2.getData();//hemşirelik için
                        String comment = (String) data2.get("comment");
                        String userEmail = (String) data2.get("useremail");
                        String downloadUrl = (String) data2.get("downloadurl");
                        //System.out.println(comment);

                        userEmailFromFB.add(userEmail);
                        userCommentFromFB.add(comment);
                        userImageFromFB.add(downloadUrl);

                        feedRecycler2.notifyDataSetChanged();


                    }
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.kullanicilar_signout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*if (item.getItemId() == R.id.kayitli_kullanicilar_item){
            Intent intent = new Intent(chatActivity.this,kullanicilarActivity.class);
            startActivity(intent);
        }*/
        if (item.getItemId() == R.id.sign_out){
            firebaseAuth.signOut();
            Intent intent2 = new Intent(chatActivity.this,giris_kayitActivity.class);
            startActivity(intent2);
        }
        else // ekleye tıklanırsa
        {
            Intent intent3 = new Intent(chatActivity.this,uploadActivity.class);
            //intent3.putExtra("kullanici_adi",aktarilan_kullanici_adi);
            startActivity(intent3);
        }

        return super.onOptionsItemSelected(item);
    }
}


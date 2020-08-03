package com.nuri.proje;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import io.grpc.Context;

public class uploadActivity extends AppCompatActivity {

    Bitmap selectedImage;
    ImageView imageView2;
    EditText editText;
    Button button3;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    Uri imageData;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    private StorageReference storageReference2;//hemsirelik için
    private FirebaseFirestore firebaseFirestore2;//hemşirelik için

    private String MAIN_DATA="com.nuri.proje.MAIN_DATA";
    private String BOLUM_INFO="com.nuri.proje.BOLUM_INFO";
    private String SINIF_INFO="com.nuri.proje.SINIF_INFO";
    String bolum_bilgisi,sinif_bilgisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        bolum_bilgisi=getSharedPreferences(MAIN_DATA,MODE_PRIVATE).getString(BOLUM_INFO,"Bölüm bilgisi bulunamadı.");
        sinif_bilgisi=getSharedPreferences(MAIN_DATA,MODE_PRIVATE).getString(SINIF_INFO,"Sınıf bilgisi bulunamadı.");
        System.out.println(bolum_bilgisi+"SAKLANAN VERİ");
        System.out.println(sinif_bilgisi+"SAKLANAN VERİ");

        imageView2 = findViewById(R.id.imageView2);
        editText = findViewById(R.id.editText);
        button3 = findViewById(R.id.button3);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        storageReference2 = firebaseStorage.getReference();
        firebaseFirestore2 = FirebaseFirestore.getInstance();


        //Intent intent3 = getIntent();
        //String kullanici_adi = intent3.getStringExtra("kullanici_adi");
        //System.out.println(kullanici_adi); //feed'de yani chat'de kullanılacak paylaşım yapan isim bu

    }

    public void gonder(View view){ //buton , upload gönderme

        if (bolum_bilgisi.equals("Bilgisayar Mühendisliği*") && sinif_bilgisi.equals("1*")){

            if (imageData != null){

                UUID uuid = UUID.randomUUID();
                final String imageName = "images/" +uuid +".jpeg";

                storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        StorageReference newReferences =  FirebaseStorage.getInstance().getReference(imageName);


                        newReferences.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String downloadUrl = uri.toString();
                                //System.out.println(downloadUrl);

                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                                String useremail = firebaseUser.getEmail();
                                String comment = editText.getText().toString();


                                HashMap<String , Object> postData = new HashMap<>();
                                postData.put("useremail",useremail);
                                postData.put("comment",comment);
                                postData.put("downloadurl",downloadUrl);
                                postData.put("date", FieldValue.serverTimestamp());

                                firebaseFirestore.collection("Posts").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Intent intenttochat = new Intent(uploadActivity.this,uploadingActivity.class);
                                        //intenttochat.putExtra("info","devam");
                                        //intenttochat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intenttochat);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(uploadActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                                    }
                                });


                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(uploadActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        }

        else if (bolum_bilgisi.equals("Hemşirelik*") && sinif_bilgisi.equals("1*")){

            if (imageData != null){

                UUID uuid2 =UUID.randomUUID();//hemşirelik için
                final String imageName2 = "images2/" +uuid2 +".jpeg";

            storageReference2.child(imageName2).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                StorageReference newReferences2 =  FirebaseStorage.getInstance().getReference(imageName2); //2lerin hepsi hemşirelik için
                newReferences2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadUrl2 = uri.toString();
                        //System.out.println(downloadUrl2);
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        String useremail = firebaseUser.getEmail();
                        String comment = editText.getText().toString();

                        HashMap<String , Object> postData2 = new HashMap<>();//hemşirelik için
                        postData2.put("useremail",useremail);
                        postData2.put("comment",comment);
                        postData2.put("downloadurl",downloadUrl2);
                        postData2.put("date", FieldValue.serverTimestamp());

                        firebaseFirestore2.collection("Posts2").add(postData2).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent intenttochat = new Intent(uploadActivity.this,uploadingActivity.class);
                                startActivity(intenttochat);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(uploadActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

            }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(uploadActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
            });

            }
        }

        else{
            button3.setVisibility(View.INVISIBLE);
        }

    }

    public void upload(View view){ //selectimage , imageview

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else
        {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGallery,2);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageData = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView2.setImageBitmap(selectedImage);
                }
                else{
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView2.setImageBitmap(selectedImage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

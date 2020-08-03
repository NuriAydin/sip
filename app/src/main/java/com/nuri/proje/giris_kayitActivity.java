package com.nuri.proje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseUser;

public class giris_kayitActivity extends AppCompatActivity {

    EditText editText5, editText7, emailText;
    Button button8, button9;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_kayit);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            Intent intent = new Intent(giris_kayitActivity.this,secimActivity.class);
            intent.putExtra("info","gec");
            startActivity(intent);
            finish();
        }

        editText5 = findViewById(R.id.editText5);
        editText7 = findViewById(R.id.editText7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        emailText = findViewById(R.id.emailText);


       /* button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kayitli_tc = editText5.getText().toString();
                Intent intent11 = new Intent(giris_kayitActivity.this,secimActivity.class);
                intent11.putExtra("kayitli_tc",kayitli_tc);
                intent11.putExtra("info","gec");
                startActivity(intent11);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(giris_kayitActivity.this,secimActivity.class);
                intent.putExtra("info","devam");
                startActivity(intent);
            }
        });

        */

    }
    @Override
    public void onBackPressed() {

        backButtonHandler();
        return;
    }
    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                giris_kayitActivity.this);
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

    public void kayit(View view) {
        String email = emailText.getText().toString();
        String password = editText7.getText().toString();
        String tc = editText5.getText().toString();

        if (email.matches("") || password.matches("") || tc.matches("")) {
            Toast.makeText(giris_kayitActivity.this, "Alanları boş bırakmayınız", Toast.LENGTH_LONG).show();

        } else
            {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(giris_kayitActivity.this, "Kullanıcı oluşturuldu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(giris_kayitActivity.this, secimActivity.class);
                    intent.putExtra("info", "devam");
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(giris_kayitActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();

                }
            });
    }

}
    public void giris(View view){
        String email = emailText.getText().toString();
        String password = editText7.getText().toString();
        String tc = editText5.getText().toString();

        if (email.matches("") || password.matches("") || tc.matches("")) {
            Toast.makeText(giris_kayitActivity.this, "Alanları boş bırakmayınız", Toast.LENGTH_LONG).show();

        } else
        {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
            Intent intentIn = new Intent(giris_kayitActivity.this,secimActivity.class);
            intentIn.putExtra("info","devam");
            startActivity(intentIn);
            finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    }
}

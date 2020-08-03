package com.nuri.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sifremi_unuttumActivity extends AppCompatActivity {

   /*
    private Button button4;
    private EditText editText3;
    private EditText editText4;
    private TextView textView7;
    private Button button5;

    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);
        /*

        Button button4 =(Button) findViewById(R.id.button4);
        final EditText editText3 =(EditText) findViewById(R.id.editText3);
        final TextView textview7 =(TextView) findViewById(R.id.textView7);
        EditText editText4 =(EditText) findViewById(R.id.editText4);
        Button button5 = (Button) findViewById(R.id.button5);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String metin = editText3.getText().toString();
            textview7.setText(metin+" "+"numarasına gelen doğrulama kodunu giriniz ");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent2 = new Intent(sifremi_unuttumActivity.this,kayitActivity.class);
            startActivity(intent2);
            }
        });
        */

    }
}

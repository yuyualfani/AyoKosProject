package com.example.yuyu.ayokos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FisrtPageUser extends AppCompatActivity {
    Button btnPencari, btnPmeilik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisrt_page_user);

        btnPencari = (Button) findViewById(R.id.buttonPencariKos);
        btnPmeilik = (Button) findViewById(R.id.buttonPemilikKos);


        btnPencari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FisrtPageUser.this, Register.class);
                startActivity(i);


            }
        });

        btnPmeilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FisrtPageUser.this, RegisterPemilik.class);
                startActivity(i);


            }
        });
    }
}

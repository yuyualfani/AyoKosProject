package com.example.yuyu.ayokos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yuyu.ayokos.routes.LihatPetunjukArah;

//import com.example.yuyu.ayokos.routes.LihatPetunjukArah;


public class Awal extends AppCompatActivity {
    private Button btnloginpem, btnloginpen, btnlihatpem, btnlihatpen,
            btnregpem, btnregpen,btninsertkamar,
            btnLihatKamar,btnLihatPemesanan,btnCobaMap,
            buttonKonfirmasiPencari2,btnRegisterAdmn,
            btnDataAdmin,btnLogout,btnChat,btnMapPetunjuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);



        btnloginpem = (Button) findViewById(R.id.buttonLoginPemilik);
        btnloginpen = (Button) findViewById(R.id.buttonLoginPencari);
        btnlihatpem = (Button) findViewById(R.id.buttonLihatPemilik);
        btnlihatpen = (Button) findViewById(R.id.buttonLihatPencari);
        btnregpem = (Button) findViewById(R.id.buttonRegisterPemilik);
        btnregpen = (Button) findViewById(R.id.buttonRegisterPencari);
        btninsertkamar = (Button) findViewById(R.id.buttonInsertKamar);
        btnLihatKamar = (Button) findViewById(R.id.buttonLihatKamar);
        btnLihatPemesanan = (Button) findViewById(R.id.buttonLihatPemesanan);
        btnCobaMap =(Button) findViewById(R.id.buttonCobaMap);
        buttonKonfirmasiPencari2 = (Button) findViewById(R.id.buttonKonfirmasiPencari2);
        btnRegisterAdmn = (Button) findViewById(R.id.buttonResgiterAdmin);
        btnDataAdmin = (Button) findViewById(R.id.buttonDataAdmin);
        btnLogout = (Button) findViewById(R.id.buttonLogout);
        btnChat = (Button) findViewById(R.id.chat);
        btnMapPetunjuk = (Button) findViewById(R.id.buttonMapPetunjuk);





        btnloginpem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(Awal.this,Login.class);
               startActivity(i);
            }
        });


        btnloginpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,Login.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear().commit();


                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        });
        btnregpem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,RegisterPemilik.class);
                startActivity(i);
            }
        });
        btnregpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,Register.class);
                startActivity(i);
            }
        });
        btnlihatpem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,LihatPemilik.class);
                startActivity(i);
            }
        });
        btnlihatpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,LihatUser.class);
                startActivity(i);
            }
        });

        btninsertkamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,Map_API.class);
                startActivity(i);
            }
        });
        btnLihatKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,LihatKamar.class);
                startActivity(i);
            }
        });
        btnLihatPemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,LihatPemesananPencari.class);
                startActivity(i);
            }
        });
        btnCobaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,MapsActivity.class);
                startActivity(i);
            }
        });
        buttonKonfirmasiPencari2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,KonfirmasiPencari.class);
                startActivity(i);
            }
        });
        btnRegisterAdmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,RegisterAdmin.class);
                startActivity(i);
            }
        });
        btnDataAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,LihatAdmin.class);
                startActivity(i);
            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,ActivityChat.class);
                startActivity(i);
            }
        });
        btnMapPetunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Awal.this,LihatPetunjukArah.class);
                startActivity(i);
            }
        });












    }
}

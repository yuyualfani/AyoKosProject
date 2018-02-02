package com.example.yuyu.ayokos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailKamarKos extends AppCompatActivity {

    Button lihatlokasi,book;
//    TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamar_kos);

        lihatlokasi = (Button) findViewById(R.id.lihatlokasi);
        book = (Button) findViewById(R.id.buttonBook);

        lihatlokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailKamarKos.this, DetailKamar.class);
                startActivityForResult(i, 672);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

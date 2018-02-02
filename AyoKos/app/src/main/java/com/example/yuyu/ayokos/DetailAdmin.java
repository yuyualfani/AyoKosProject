package com.example.yuyu.ayokos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuyu.ayokos.model.Admin;
import com.example.yuyu.ayokos.model.PemilikKos;

public class DetailAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);

        Intent i = getIntent();
        final Long idAdmin= i.getLongExtra("adminId",0L);
        Admin pemilik = new Admin();

        String message = i.getStringExtra("nama");
        TextView text = (TextView) findViewById(R.id.nama);
        text.setText(message);


        String message1 = i.getStringExtra("alamat");
        TextView text1 = (TextView) findViewById(R.id.alamat);
        text1.setText(message1);


        String message2 = i.getStringExtra("email");
        TextView text2 = (TextView) findViewById(R.id.email);
        text2.setText(message2);

        Button btnEdit;

        btnEdit = (Button) findViewById(R.id.buttonEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailAdmin.this, EditAdmin.class);

                Bundle bs = getIntent().getExtras();
                Bundle b = new Bundle();
                b.putString("id",bs.getString("id"));
                b.putString("n", bs.getString("n"));
                b.putString("a", bs.getString("a"));
                b.putString("e", bs.getString("e"));
                b.putString("u", bs.getString("u"));

                i.putExtras(b);
                startActivity(i);


            }
        });


    }
}

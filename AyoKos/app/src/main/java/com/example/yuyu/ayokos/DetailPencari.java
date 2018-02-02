package com.example.yuyu.ayokos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuyu.ayokos.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetailPencari extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pencari);


        Intent i = getIntent();
        final Long idPencari = i.getLongExtra("pencariId", 0L);
        //    final User detailPencari = User.findById(User.class,idPencari);
        User detailPecnari = new User();

//
//        nama = (TextView)findViewById(R.id.nama);
//        nama.setText(detailPecnari.getNama());
//        i.getStringExtra("nama");
//
//        System.out.println("nama" + "yuyu");


        String message = i.getStringExtra("nama");
        TextView text = (TextView) findViewById(R.id.nama);
        text.setText(message);


        String message1 = i.getStringExtra("alamat");
        TextView text1 = (TextView) findViewById(R.id.alamat);
        text1.setText(message1);


        String message2 = i.getStringExtra("email");
        TextView text2 = (TextView) findViewById(R.id.email);
        text2.setText(message2);


//        String message3 = i.getStringExtra("foto");
//            ImageView text3 = (ImageView) findViewById(R.id.foto);
//        text3.setImageURI(message3);


        ImageView img2;
        Button btnEdit, btnDel;

        btnEdit = (Button) findViewById(R.id.buttonEdit);
        btnDel = (Button) findViewById(R.id.butttonDelet);
        img2 = (ImageView) findViewById(R.id.foto3);


        Bundle bs = getIntent().getExtras();
        String foto = bs.getString("foto");

        ImageView im = (ImageView) findViewById(R.id.foto);

        loadImageFromUrl(foto, im);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(DetailPencari.this, EditPencari.class);


                Bundle bs = getIntent().getExtras();
                Bundle b = new Bundle();
                b.putString("id", bs.getString("id"));
                b.putString("n", bs.getString("n"));
                b.putString("a", bs.getString("a"));
                b.putString("e", bs.getString("e"));
                b.putString("u", bs.getString("u"));

//                i.putExtra("pencariId", i.getStringExtra("pencariId"));
//                i.putExtra("nama",i.getStringExtra("nama"));
//                i.putExtra("alamat",i.getStringExtra("alamat"));
//                i.putExtra("email",i.getStringExtra("email"));
//                i.putExtra("foto",i.getStringExtra("foto"));

                i.putExtras(b);

                startActivity(i);


            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bb = getIntent().getExtras();
                //Bundle bc = new Bundle();
                String a = bb.getString("id");

                DatabaseReference db;
                db = FirebaseDatabase.getInstance().getReference("TABLE_PENCARIKOS");
                db.child(a).removeValue();
                System.out.println(a + " pllll");


            }
        });


    }
    private void loadImageFromUrl(String uri,ImageView bukti){
        Picasso.with(this).load(uri).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(bukti, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }





    }


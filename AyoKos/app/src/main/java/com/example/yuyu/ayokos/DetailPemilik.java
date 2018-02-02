package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuyu.ayokos.model.PemilikKos;
import com.example.yuyu.ayokos.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class DetailPemilik extends AppCompatActivity {
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;

    private static final int SELECT_FOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemilik);
        Intent i = getIntent();
        final Long idPemilik= i.getLongExtra("pemilikId",0L);
        PemilikKos pemilik = new PemilikKos();



//        Intent i = getIntent();
//        final Long idPemilik= i.getLongExtra("pemilikId",0L);
//        //    final User detailPencari = User.findById(User.class,idPencari);
//        PemilikKos detailPemilik = new PemilikKos();
//
//
        String message = i.getStringExtra("nama");
        TextView text = (TextView) findViewById(R.id.nama);
        text.setText(message);


        String message1 = i.getStringExtra("alamat");
        TextView text1 = (TextView) findViewById(R.id.alamat);
        text1.setText(message1);


        String message2 = i.getStringExtra("email");
        TextView text2 = (TextView) findViewById(R.id.email);
        text2.setText(message2);


        Bundle bs = getIntent().getExtras();
        String foto = bs.getString("foto");

        ImageView im = (ImageView) findViewById(R.id.foto3);

        loadImageFromUrl(foto, im);


        Button btnEdit,btnDelete;

        btnEdit = (Button) findViewById(R.id.buttonEdit);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailPemilik.this, EditPemilik.class);

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
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bb = getIntent().getExtras();
                Bundle bc = new Bundle();
               String a = bb.getString("id");

                DatabaseReference db;
                db = FirebaseDatabase.getInstance().getReference("TABLE_PEMILIKKOS");
                db.child(a).removeValue();
                System.out.println(a+" pllll");





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

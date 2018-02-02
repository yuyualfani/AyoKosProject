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

import com.example.yuyu.ayokos.model.Kamar;
import com.example.yuyu.ayokos.model.Pemesanan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailPemesanan extends AppCompatActivity {
    private Button btnKonfirmasi;
    DatabaseReference databaseReference;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;
    private ArrayList<Pemesanan> rvData;
    String iddb = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan);


        btnKonfirmasi = (Button) findViewById(R.id.buttonKonfirmasi);
        databaseReference = FirebaseDatabase.getInstance().getReference("TABLE_PEMESANAN");

        Intent i = getIntent();
        final Long idPemesanan= i.getLongExtra("pemesananId",0L);

//        Kamar kamar = new Kamar();
//        i.putExtra("kamarId", kamar.getId());



//        String message = i.getStringExtra("namakos");
//        TextView text = (TextView) findViewById(R.id.namakos);
//        text.setText(message);


        String message1 = i.getStringExtra("namapencari");
        TextView text1 = (TextView) findViewById(R.id.namapemesan);
        text1.setText(message1);


        String message2 = i.getStringExtra("namapemilik");
        TextView text2 = (TextView) findViewById(R.id.namapemilik);
        text2.setText(message2);


        String message3 = i.getStringExtra("status");
        TextView text3 = (TextView) findViewById(R.id.status);
        text3.setText(message3);

        String message4 = i.getStringExtra("jumlah");
        TextView text4 = (TextView) findViewById(R.id.jumlah);
        text4.setText(message4);


        String message5 = i.getStringExtra("tanggal");
        TextView text5 = (TextView) findViewById(R.id.tanggalpemesanan);
        text5.setText(message5);


//        Bundle b = new Bundle();
//       // String foto = b.getString("foto");
//
//        String foto2 = b.getString("foto");

//        ImageView im = (ImageView) findViewById(R.id.bukrikonfirmasi);
//
//        Bundle bs = getIntent().getExtras();
//        String foto2 = bs.getString("foto");
//
//       // ImageView im = (ImageView) findViewById(R.id.fotokamar);
//
//        loadImageFromUrl(foto2, im);

       Bundle bs = getIntent().getExtras();
        String foto= bs.getString("foto");
//
      ImageView im = (ImageView) findViewById(R.id.bukrikonfirmasi);
//
        loadImageFromUrl(foto, im);




//
       final Bundle bsd = getIntent().getExtras();

//        if(!bs.getString("status__").equals("durung bayar")){
//            btnKonfirmasi.setVisibility(View.GONE);
//        }


        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iddb = bsd.getString("pemesananId_");

                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Pemesanan pemesanan = dataSnapshot.getValue(Pemesanan.class);
                        if(pemesanan.getId_pemesanan().toString().trim().equals(iddb.toString())){
                            addPemesanan(pemesanan.getId_pemesanan(),pemesanan.getId_pencari(),pemesanan.getId_kamar(),
                          pemesanan.getNamappencari(),pemesanan.getNamakos(),pemesanan.getNamapemilik(),pemesanan.getJumlah(),""  );
                            btnKonfirmasi.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });


            }
        });
    }

    public void addPemesanan(String iddb, String idPen,String idKam,String namapencari ,String namakos,String namapemilik, String jumlah,String total)
    {
        Pemesanan pemesanan = new Pemesanan(iddb,idPen,idKam,namapencari,namakos,namapemilik,jumlah,"bayar","23/01/2018","","");
        databaseReference.child(iddb).setValue(pemesanan);


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

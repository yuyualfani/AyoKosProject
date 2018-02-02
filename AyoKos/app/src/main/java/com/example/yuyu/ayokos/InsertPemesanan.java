package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.yuyu.ayokos.model.Kamar;
import com.example.yuyu.ayokos.model.Pemesanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class InsertPemesanan extends AppCompatActivity {
    private EditText nama,jumlah;
    private Button btnSavePemesanan,btnNotif;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db, db2;
    private FirebaseDatabase database;
//    private Uri uri;
//    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
//    private StorageReference imageRef,storageRef;

//    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pemesanan);

        db= FirebaseDatabase.getInstance().getReference("TABLE_PEMESANAN");

        btnSavePemesanan = (Button) findViewById(R.id.buttonSavePemesanan);
        btnNotif = (Button) findViewById(R.id.buttonNotif);
       // nama = (EditText) findViewById(R.id.namapemesan);
        jumlah = (EditText) findViewById(R.id.jumlah);






//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = sharedPreferences.edit();
//                edit.clear().commit();
//
//
//                Intent i = new Intent(getApplicationContext(), Login.class);
//                startActivity(i);
//                finish();
//            }
//        });
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertPemesanan.this, NotifKeUser.class);

                startActivity(i);
            }
        });
        btnSavePemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//
//                SharedPreferences sharedPreferences =  getSharedPreferences("userInfo",Context.MODE_PRIVATE);
//
//                String idd = sharedPreferences.getString("id","");
//                String nama1 = sharedPreferences.getString("nama","");
//                addKamar(nama.getText().toString().trim(), harga.getText().toString().trim(),
//                        stock.getText().toString().trim()," ",idd,nama1);
//                System.out.println("hfgvkjmhnbyikjghngbvd=");



                SharedPreferences sharedPreferences =  getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                String idd = sharedPreferences.getString("id","");
                String nama2 = sharedPreferences.getString("nama","");


              //  final Long idKamar= i.getLongExtra("kamarId",0L);

                Bundle b = getIntent().getExtras();
                final String idKamar = b.getString("kamarId");
                String namakos = b.getString("nama");
              String namapemilik = b.getString("namapemilik");
                String harga2 =  b.getString("harga");



              int total2 = Integer.parseInt(b.getString("harga")) * Integer.parseInt(jumlah.getText().toString().trim());




                addPemesanan(idd,idKamar.toString().trim(),nama2,namakos,namapemilik,jumlah.getText().toString(),"",String.valueOf(total2));
               System.out.println(idKamar + idd + namakos + namapemilik +"hfgvkjmhnbyikjghngbvd=");

                addKamar(idKamar, jumlah);

                System.out.println("yuyuyu " + total2 + " " + harga2 + jumlah.getText());

    }

    public  void addKamar(final String idKamar, final EditText jumlah){

        final DatabaseReference dbsss= FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");

        dbsss.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Kamar k = dataSnapshot.getValue(Kamar.class);

                if (k.getId().toString().trim().equals(idKamar)){
                    int stokTerakhir = Integer.parseInt(k.getStock().toString().trim()) - Integer.parseInt(jumlah.getText().toString().trim());

                    Kamar u = new Kamar(k.getId(), k.getNama(), k.getHarga(), String.valueOf(stokTerakhir), k.getUrll(), k.getIdPm(), k.getNamaPemilik()
                            , k.getLatitude(), k.getLongitude());
                    dbsss.child(idKamar).setValue(u);
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

//    delete.on
//    {
//        bundle bdsv sd =nen
//        db.child(bs.getSTr("id")).removeValue();
//    }

    public void addPemesanan(String idPen,String idKam,String namapencari ,String namakos,String namapemilik, String jumlah,String tandabukti,String total)
    {
        String id = db.push().getKey();
        Pemesanan pemesanan = new Pemesanan(id,idPen,idKam,namapencari,namakos,namapemilik,jumlah,"durung bayar","29/01/2018","",total);
        db.child(id).setValue(pemesanan);
//        db.child(id).removeValue();


    }

    public  void notif(final String idKamar, final EditText jumlah){

        final DatabaseReference dbsss= FirebaseDatabase.getInstance().getReference("TABLE_PEMESANAN");

        dbsss.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Kamar k = dataSnapshot.getValue(Kamar.class);

                if (k.getId().toString().trim().equals(idKamar)){
                    int hargaakhir = Integer.parseInt(k.getHarga().toString().trim()) * Integer.parseInt(jumlah.getText().toString().trim());

                    Kamar u = new Kamar(k.getId(), k.getNama(), k.getHarga(), String.valueOf(hargaakhir), k.getUrll(), k.getIdPm(), k.getNamaPemilik()
                            , k.getLatitude(), k.getLongitude());
                    dbsss.child(idKamar).setValue(u);
                    System.out.println(" ojkhg ijuhygfdsasdxfcgvbhjnkl;");
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

}


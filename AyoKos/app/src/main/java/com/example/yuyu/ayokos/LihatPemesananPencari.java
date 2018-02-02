package com.example.yuyu.ayokos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yuyu.ayokos.adapater.PemesananAdapter;
import com.example.yuyu.ayokos.adapater.PemilikKosAdapter;
import com.example.yuyu.ayokos.model.Pemesanan;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LihatPemesananPencari extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Pemesanan> list = new ArrayList<>();
    PemesananAdapter mAdapter;
    Button btnKonfirmasi;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pemesanan_pencari);

        SharedPreferences sharedPreferences =  getSharedPreferences("userInfo", Context.MODE_PRIVATE);



        recyclerView = (RecyclerView) findViewById(R.id.recycleviewPemesanan);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PemesananAdapter(LihatPemesananPencari.this, list, sharedPreferences);
        recyclerView.setAdapter(mAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("TABLE_PEMESANAN");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Pemesanan pemesanan = dataSnapshot.getValue(Pemesanan.class);
                lihatData(pemesanan.getId_pemesanan().toString(),pemesanan.getId_pencari().toString(),

                        pemesanan.getId_kamar().toString(), pemesanan.getNamappencari().toString(),
                        "", pemesanan.getNamapemilik().toString(),
                        pemesanan.getJumlah().toString(), pemesanan.getStatus().toString(),
                        pemesanan.getTandabukti().toString(),pemesanan.getTanggal().toString(),"");
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

  //  id_pemesanan,id_pencari,id_kamar,namappencari,namakos,namapemilik,jumlah,status,tanggal
  //String id_pemesanan,id_pencari,id_kamar,namappencari,
         // namakos,namapemilik,jumlah,status,tanggal,tandabukti,total;
    public  void lihatData(String id_pemesanan,String id_pencari,String id_kamar,String namappencari, String namakos,String namapemilik,String jumlah,String status,String tanggal,String tandabukti,String total)
    {


        Pemesanan u = new Pemesanan(id_pemesanan,id_pencari,id_kamar,namappencari,namakos,namapemilik,jumlah,status,tanggal,tandabukti,"");
        list.add(u);
        mAdapter.notifyDataSetChanged();




    }

    }



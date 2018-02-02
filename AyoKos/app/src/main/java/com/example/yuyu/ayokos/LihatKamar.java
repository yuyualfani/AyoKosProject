package com.example.yuyu.ayokos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yuyu.ayokos.adapater.KamarAdapter;
import com.example.yuyu.ayokos.adapater.PemilikKosAdapter;
import com.example.yuyu.ayokos.model.Kamar;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LihatKamar extends AppCompatActivity {
    RecyclerView recyclerViewKamar;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Kamar> list = new ArrayList<>();
    KamarAdapter mAdapter;

    Button btnRegister;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kamar);

        recyclerViewKamar = (RecyclerView)findViewById(R.id.recycleviewKamar);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewKamar.setLayoutManager(layoutManager);
        mAdapter = new KamarAdapter(LihatKamar.this, list);
        recyclerViewKamar.setAdapter(mAdapter);
        btnRegister = (Button) findViewById(R.id.RegisterPencari);


        databaseReference = FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Kamar kamar = dataSnapshot.getValue(Kamar.class);
              //  lihatData(pemilik.getId().toString(),pemilik.getNama().toString(),pemilik.getAlamat().toString(),pemilik.getEmail().toString(),pemilik.getUrl().toString());
                lihatKamar(kamar.getId().toString(),kamar.getNama().toString(),kamar.getHarga().toString(),
                        kamar.getStock().toString(),kamar.getIdPm().toString(),kamar.getUrll().toString(),
                        kamar.getNamaPemilik().toString(),kamar.getLatitude().toString(), kamar.getLongitude().toString());
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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LihatKamar.this, RegisterPemilik.class);
                startActivity(i);
            }
        });

    }


    public void lihatKamar(String id,String nama,String harga,String stock,String id_pemilik,String urll, String namapemilik,String latitude, String longitude)
    {
        Kamar u = new Kamar(id,nama,harga,stock,urll,id_pemilik,namapemilik,latitude,longitude);
        list.add(u);
        mAdapter.notifyDataSetChanged();
    }


}

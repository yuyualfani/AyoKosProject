package com.example.yuyu.ayokos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yuyu.ayokos.adapater.PemilikKosAdapter;
import com.example.yuyu.ayokos.adapater.UserAdapter;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.example.yuyu.ayokos.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LihatPemilik extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PemilikKos> list = new ArrayList<>();
    PemilikKosAdapter mAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pemilik);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PemilikKosAdapter(LihatPemilik.this, list);
        recyclerView.setAdapter(mAdapter);



        databaseReference = FirebaseDatabase.getInstance().getReference("TABLE_PEMILIKKOS");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PemilikKos pemilik = dataSnapshot.getValue(PemilikKos.class);
                lihatData(pemilik.getId().toString(),pemilik.getNama().toString(),pemilik.getAlamat().toString(),pemilik.getEmail().toString(),pemilik.getUrl().toString());



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

    public  void lihatData(String id,String nama, String alamat, String email, String urll)
    {


        PemilikKos u = new PemilikKos(id,nama,alamat,email, urll);
        list.add(u);
        mAdapter.notifyDataSetChanged();




    }

}

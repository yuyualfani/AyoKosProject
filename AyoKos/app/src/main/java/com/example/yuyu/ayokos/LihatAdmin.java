package com.example.yuyu.ayokos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.yuyu.ayokos.adapater.AdminAdapter;
import com.example.yuyu.ayokos.adapater.KamarAdapter;
import com.example.yuyu.ayokos.adapater.PemesananAdapter;
import com.example.yuyu.ayokos.model.Admin;
import com.example.yuyu.ayokos.model.Pemesanan;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LihatAdmin extends AppCompatActivity {

    RecyclerView recyclerViewAdmin;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Admin> list = new ArrayList<>();
    AdminAdapter mAdapter;
    Button btnKonfirmasi;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_admin);

        recyclerViewAdmin = (RecyclerView)findViewById(R.id.recycleviewAdmin);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewAdmin.setLayoutManager(layoutManager);
        mAdapter = new AdminAdapter(LihatAdmin.this, list);
        recyclerViewAdmin.setAdapter(mAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("TABLE_ADMIN");


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Admin adm = dataSnapshot.getValue(Admin.class);
                lihatData(adm.getId().toString(),adm.getNama().toString(),adm.getAlamat().toString(),adm.getEmail().toString(),adm.getUrl().toString());
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


        Admin u = new Admin(id,nama,alamat,email, urll);
        list.add(u);
        mAdapter.notifyDataSetChanged();




    }

}


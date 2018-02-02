package com.example.yuyu.ayokos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yuyu.ayokos.model.PemilikKos;
import com.example.yuyu.ayokos.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    public FirebaseAuth auth;
    public EditText email,password;
    public Button btnLogin;

    DatabaseReference pemilik, userbiasa,admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.buttonLogin);

        pemilik = FirebaseDatabase.getInstance().getReference("TABLE_PEMILIKKOS");
        userbiasa = FirebaseDatabase.getInstance().getReference("TABLE_PENCARIKOS");
        admin = FirebaseDatabase.getInstance().getReference("TABLE_ADMIN");

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()){
                    (auth.signInWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                userbiasa.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        User u = dataSnapshot.getValue(User.class);


                                        if (u.getEmail().toString().trim().equals(email.getText().toString().trim())){
                                            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPref.edit();

//                                            editor.putString("id_pencari", u.getId());
                                            editor.putString("id",u.getId());
                                            editor.putString("email", u.getEmail());
                                            editor.putString("nama", u.getNama());
                                            editor.putString("user","biasa");
                                            editor.apply();
//
//                                            Intent i = new Intent(Login.this, LihatKamar.class);
//                                            startActivity(i);
                                           // finish();
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

                                pemilik.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        PemilikKos p = dataSnapshot.getValue(PemilikKos.class);

                                        if (p.getEmail().toString().trim().equals(email.getText().toString().trim())){
                                            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPref.edit();

                                            editor.putString("id", p.getId());
                                            editor.putString("email", p.getEmail());
                                            editor.putString("nama", p.getNama());
                                            editor.putString("user","pemilik");
                                            editor.apply();

//                                            Intent i = new Intent(Login.this, InsertKamar.class);
//                                            startActivity(i);
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

                                admin.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        PemilikKos p = dataSnapshot.getValue(PemilikKos.class);

                                        if (p.getEmail().toString().trim().equals(email.getText().toString().trim())){
                                            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPref.edit();

                                            editor.putString("id", p.getId());
                                            editor.putString("email", p.getEmail());
                                            editor.putString("nama", p.getNama());
                                            editor.putString("user","admin");
                                            editor.apply();

                                            Intent i = new Intent(Login.this, LihatPemesananPencari.class);
                                            startActivity(i);
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






                            }else{
                                pesan();
                            }
                        }
                    });

                }
            }
        });


    }

    private void pesan(){
        Toast.makeText(Login.this, "Email/password salah",Toast.LENGTH_SHORT).show();
    }

}
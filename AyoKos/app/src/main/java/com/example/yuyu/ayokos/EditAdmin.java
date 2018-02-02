package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yuyu.ayokos.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditAdmin extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference db;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;

    EditText nama,alamat,email;
    Button btnEdit;

    Intent ias = getIntent();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);

        nama = (EditText) findViewById(R.id.namaedit);
        alamat = (EditText) findViewById(R.id.alamatedit);
        email = (EditText) findViewById(R.id.emailemail);
        btnEdit = (Button) findViewById(R.id.buttonEdit);



        db= FirebaseDatabase.getInstance().getReference("TABLE_ADMIN");

        Intent ias = getIntent();

        final Bundle bs = getIntent().getExtras();
        nama.setText(bs.getString("n"));
        alamat.setText(bs.getString("a"));
        email.setText(bs.getString("e"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();

                Bundle bs = getIntent().getExtras();
                Bundle b = new Bundle();
                b.putString("id",bs.getString("id"));
                b.putString("n", bs.getString("n"));
                b.putString("a", bs.getString("a"));
                b.putString("e", bs.getString("e"));
                b.putString("u", bs.getString("u"));
                addData(bs.getString("id"), nama.getText().toString(),alamat.getText().toString(),
                        email.getText().toString(), bs.getString("u") );
                btnEdit.setVisibility(View.GONE);

            }
        });
    }
    public  void addData(String idd ,String nama, String alamat, String email, String urll) {
        User u = new User(idd, nama, alamat, email, urll);
        db.child(idd).setValue(u);
    }
}

package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuyu.ayokos.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditPemilik extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference db;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;

    EditText nama,alamat,email;
    ImageView img;
    Button btnEdit;

    private static final int SELECT_FOTO = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pemilik);

        nama = (EditText) findViewById(R.id.namaedit);
        alamat = (EditText) findViewById(R.id.alamatedit);
        email = (EditText) findViewById(R.id.emailemail);
        btnEdit = (Button) findViewById(R.id.buttonEdit);
        img = (ImageView) findViewById(R.id.fotocu);


        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        db= FirebaseDatabase.getInstance().getReference("TABLE_PEMILIKKOS");
        Intent ias = getIntent();

        final Bundle bs = getIntent().getExtras();
        nama.setText(bs.getString("n"));
       alamat.setText(bs.getString("a"));
        email.setText(bs.getString("e"));


        final String foto = bs.getString("u");

    //    ImageView im = (ImageView) findViewById(R.id.foto);

       loadImageFromUrl(foto, img);





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
                System.out.println(foto+"jkhgfds");
                btnEdit.setVisibility(View.GONE);

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), SELECT_FOTO);
            }

        });

    }



    public void uploadImage(Uri selectedImagea) {
        imageRef = storageRef.child("images/" + selectedImagea.getLastPathSegment());
        //creating and showing progress dialog


        //starting upload
        uploadTask = imageRef.putFile(selectedImagea);
        // Observe state change events such as progress, pause, and resume

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //sets and increments value of progressbar
            }
        });

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditPemilik.this, "Error in uploading!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(EditPemilik.this, "Upload successful", Toast.LENGTH_SHORT).show();



//                addData(nama.getText().toString().trim(),alamat.getText().toString().trim(),
//                        inputEmail.getText().toString().trim(), downloadUrl.toString());



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SELECT_FOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            img.setImageURI(uri);
            Toast.makeText(this, String.valueOf(uri), Toast.LENGTH_LONG).show();

        }



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

    public void addData(String idd, String nama, String alamat, String email, String urll)
    {
        User u = new User(idd,nama,alamat,email,urll);
        db.child(idd).setValue(u);
    }
}

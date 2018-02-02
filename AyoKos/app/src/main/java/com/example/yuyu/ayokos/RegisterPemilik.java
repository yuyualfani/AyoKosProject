package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yuyu.ayokos.model.PemilikKos;
import com.example.yuyu.ayokos.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterPemilik extends AppCompatActivity {


    private EditText inputEmail, inputPassword,nama,alamat;
    private ImageView foto;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;

    private static final int SELECT_FOTO = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pemilik);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference("TABLE_PEMILIKKOS");

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        foto = (ImageView) findViewById(R.id.foto);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);


        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPemilik.this, ResetPassword.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterPemilik.this,Login.class);
                startActivityForResult(i,1);


            }
        });



        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), SELECT_FOTO);
            }

        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(RegisterPemilik.this,Login.class);
                startActivityForResult(i,1);
            }
        });




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterPemilik.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterPemilik.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterPemilik.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    startActivity(new Intent(RegisterPemilik.this,Login.class));

                                    uploadImage(uri);

                                    finish();


                                }
                            }
                        });

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
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
                Toast.makeText(RegisterPemilik.this, "Error in uploading!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(RegisterPemilik.this, "Upload successful", Toast.LENGTH_SHORT).show();



                addData(nama.getText().toString().trim(),alamat.getText().toString().trim(),
                        inputEmail.getText().toString().trim(), downloadUrl.toString());



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SELECT_FOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            foto.setImageURI(uri);
            Toast.makeText(this, String.valueOf(uri), Toast.LENGTH_LONG).show();

        }



    }


    public  void addData(String nama, String alamat, String email, String urll)
    {
        String id = db.push().getKey();
        PemilikKos u = new PemilikKos(id,nama,alamat,email, urll);
        db.child(id).setValue(u);



    }

}

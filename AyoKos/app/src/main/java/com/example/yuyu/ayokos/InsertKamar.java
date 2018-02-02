//package com.example.yuyu.ayokos;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.yuyu.ayokos.model.Kamar;
//import com.example.yuyu.ayokos.model.User;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//public class InsertKamar extends AppCompatActivity {
//    private EditText nama,stock,harga;
//    private ImageView foto;
//    private Button btnSavekamar, logout;
//    private ProgressBar progressBar;
//       private FirebaseAuth auth;
//    private DatabaseReference db;
//    private Uri uri;
//    private FirebaseStorage storage;
//    private ProgressDialog progressDialog;
//    private StorageReference imageRef,storageRef;
//    UploadTask uploadTask;
//
//    private static final int SELECT_FOTO = 100;
//    private FirebaseDatabase database;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_insert_kamar);
//
//
//        db= FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");
//
//        btnSavekamar = (Button) findViewById(R.id.buttonSaveKamarr);
//        nama = (EditText) findViewById(R.id.namakos);
//        harga = (EditText) findViewById(R.id.harga);
//        stock = (EditText) findViewById(R.id.stock);
//        logout = (Button)findViewById(R.id.buttonLogout);
//
//        foto = (ImageView) findViewById(R.id.foto);
//        storage = FirebaseStorage.getInstance();
//        storageRef = storage.getReference();
//
//        btnSavekamar.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View view) {
//
//uploadImage(uri);
//
//
//
//          }
//
//
//
//
//        });
//
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
//
//        foto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Pilih Foto Kamar"), SELECT_FOTO);
//            }
//
//        });}
//
//    public void uploadImage(Uri selectedImagea) {
//        imageRef = storageRef.child("images/" + selectedImagea.getLastPathSegment());
//        //creating and showing progress dialog
//
//
//        //starting upload
//        uploadTask = imageRef.putFile(selectedImagea);
//        // Observe state change events such as progress, pause, and resume
//
//        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @SuppressWarnings("VisibleForTests")
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                //sets and increments value of progressbar
//            }
//        });
//
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(InsertKamar.this, "Error in uploading!", Toast.LENGTH_SHORT).show();
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @SuppressWarnings("VisibleForTests")
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                Toast.makeText(InsertKamar.this, "Upload successful", Toast.LENGTH_SHORT).show();
//
////                Bundle bs = getIntent().getExtras();
////                String idpemesanan = bs.getString("pemesananId_");
//
////                b.putString("id_kmr", pemesanan.getId_kamar());
////                b.putString("pemesananId_", pemesanan.getId_pemesanan());
////                b.putString("id_pecari", pemesanan.getId_pencari());
////                b.putString("jumlah", pemesanan.getJumlah());
////                b.putString("namaKost", pemesanan.getNamakos());
////                b.putString("namapemilik", pemesanan.getNamapemilik());
////                b.putString("namapencari", pemesanan.getNamappencari());
////                b.putString("tanggal", pemesanan.getTanggal());
////                String idpemesana, String idPen,String idKam,String namapencari ,String namakos,String namapemilik, String jumlah,String tandabukti)
////                {
//
////
////                SharedPreferences sharedPreferences =  getSharedPreferences("userInfo",Context.MODE_PRIVATE);
////                String idd = sharedPreferences.getString("id","");
////                String nama1 = sharedPreferences.getString("nama","");
////                addKamar(nama.getText().toString().trim(), harga.getText().toString().trim(),
////                        stock.getText().toString().trim(), downloadUrl.toString(),idd,nama1);
////                System.out.println("hfgvkjmhnbyikjghngbvd=");
////              //  uploadImage(uri);
////
////                System.out.println(downloadUrl.toString() + " dsxfgcvbhjnkmlmknjbhgyuhj");
//
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SELECT_FOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
//            uri = data.getData();
//            foto.setImageURI(uri);
//            Toast.makeText(this, String.valueOf(uri), Toast.LENGTH_LONG).show();
//        }
// }
//
//    public  void addKamar(String nama, String harga, String stock, String urll, String idPm,String namapemilik)
//    {
//        String id = db.push().getKey();
//        Kamar u = new Kamar(id,nama,harga,stock, urll, idPm,namapemilik);
//        db.child(id).setValue(u);
//    }
//
//
//}
//
//
//
//

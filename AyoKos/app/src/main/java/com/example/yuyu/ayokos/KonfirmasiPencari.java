package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yuyu.ayokos.model.Pemesanan;
import com.example.yuyu.ayokos.model.PemilikKos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class KonfirmasiPencari extends AppCompatActivity {

    private ImageView tandabukti;
    private Button btnKonfirmasiPencari;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    String idb ="";
    UploadTask uploadTask;

    private static final int SELECT_FOTO = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pencari);

        db= FirebaseDatabase.getInstance().getReference("TABLE_PEMESANAN");

        tandabukti = (ImageView) findViewById(R.id.tandabukti);
        btnKonfirmasiPencari = (Button) findViewById(R.id.buttonKonfirmasiPencari);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        tandabukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), SELECT_FOTO);
            }

        });

        btnKonfirmasiPencari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            uploadImage(uri);
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
                Toast.makeText(KonfirmasiPencari.this, "Error in uploading!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(KonfirmasiPencari.this, "Upload successful", Toast.LENGTH_SHORT).show();

                Bundle bs = getIntent().getExtras();
                String idpemesanan = bs.getString("pemesananId_");
                System.out.println(idpemesanan);
//                b.putString("id_kmr", pemesanan.getId_kamar());
//                b.putString("pemesananId_", pemesanan.getId_pemesanan());
//                b.putString("id_pecari", pemesanan.getId_pencari());
//                b.putString("jumlah", pemesanan.getJumlah());
//                b.putString("namaKost", pemesanan.getNamakos());
//                b.putString("namapemilik", pemesanan.getNamapemilik());
//                b.putString("namapencari", pemesanan.getNamappencari());
//                b.putString("tanggal", pemesanan.getTanggal());
//                String idpemesana, String idPen,String idKam,String namapencari ,String namakos,String namapemilik, String jumlah,String tandabukti)
//                {



               addTandaBukti(idpemesanan,bs.getString("id_pecari"),bs.getString("id_kmr"),bs.getString("namapencari"),
                       bs.getString("namaKost"),bs.getString("namapemilik"),bs.getString("jumlah"), downloadUrl.toString(),"");
//                System.out.println(downloadUrl.toString() + " dsxfgcvbhjnkmlmknjbhgyuhj");




            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SELECT_FOTO && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            tandabukti.setImageURI(uri);
            Toast.makeText(this, String.valueOf(uri), Toast.LENGTH_LONG).show();

        }


    }

      public void addTandaBukti(String idpemesana, String idPen,String idKam,String namapencari ,String namakos,String namapemilik, String jumlah,String tandabukti,String total)
    {
        Pemesanan pemesanan = new Pemesanan(idpemesana,idPen,idKam,namapencari,namakos,namapemilik,jumlah,
                "bayar","29/01/2018",tandabukti,total);
        db.child(idpemesana).setValue(pemesanan);


    }
}

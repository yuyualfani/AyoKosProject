package com.example.yuyu.ayokos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.example.yuyu.ayokos.model.Kamar;
import com.google.android.gms.location.LocationServices;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Map_API extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        View.OnClickListener {

    private EditText nama1,stock,harga;
    private ImageView foto;
    private Button btnSavekamar, logout;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;

    private static final int SELECT_FOTO = 100;
    private FirebaseDatabase database;






    private GoogleMap mMap;
    private double longitude;
    private double latitude;



    //Buttons
    private ImageButton buttonSave;
    private ImageButton buttonCurrent;
    private ImageButton buttonView;


    //Google ApiClient
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__api);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initializing googleapi client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //Initializing views and adding onclick listeners
        buttonSave = (ImageButton) findViewById(R.id.buttonSave);
//        buttonCurrent = (ImageButton) findViewById(R.id.buttonCurrent);
//        buttonView = (ImageButton) findViewById(R.id.buttonView);

//        buttonSave.setOnClickListener(this);
//        buttonCurrent.setOnClickListener(this);
//        buttonView.setOnClickListener(this);


    db= FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");

        btnSavekamar = (Button) findViewById(R.id.buttonSaveKamarr);
        nama1 = (EditText) findViewById(R.id.namakos);
        harga = (EditText) findViewById(R.id.harga);
        stock = (EditText) findViewById(R.id.stock);
        logout = (Button)findViewById(R.id.buttonLogout);

        foto = (ImageView) findViewById(R.id.foto);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();


        btnSavekamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage(uri);



            }




        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Foto Kamar"), SELECT_FOTO);
            }

        });
        btnSavekamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage(uri);
            }
        });



    }


    String msg1 = latitude + ", "+longitude;
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Map_API() {
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

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
                Toast.makeText(Map_API.this, "Error in uploading!", Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Toast.makeText(Map_API.this, "Upload successful", Toast.LENGTH_SHORT).show();
                String msg1 = latitude + ", "+longitude;
                String a =String.valueOf(latitude);
                String b =String.valueOf(longitude);




                SharedPreferences sharedPreferences =  getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String idd = sharedPreferences.getString("id","");
                String nama2 = sharedPreferences.getString("nama","");

                addKamar(nama1.getText().toString().trim(), harga.getText().toString().trim(),
                        stock.getText().toString().trim(), downloadUrl.toString(),idd,nama2,a,b);
               // System.out.println("hfgvkjmhnbyikjghngbvd=");
              //  uploadImage(uri);

                System.out.println(downloadUrl.toString() + " dsxfgcvbhjnkmlmknjbhgyuhj");
                System.out.println(msg1 + " micin");

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

    public  void addKamar(String nama, String harga, String stock, String urll, String idPm,String namapemilik,String latitude, String longitude)
    {
        String id = db.push().getKey();
        Kamar u = new Kamar(id,nama,harga,stock, urll, idPm,namapemilik,latitude,longitude);
        db.child(id).setValue(u);
    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
//        //Initializing our map
//        mMap = googleMap;
//        //Creating a random coordinate
//        LatLng latLng = new LatLng(-34, 151);
//        //Adding marker to that coordinate
//        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        //Setting onMarkerDragListener to track the marker drag
//        mMap.setOnMarkerDragListener(this);
//        //Adding a long click listener to the map
//        mMap.setOnMapLongClickListener(this);



//        MapsInitializer.initialize(getContext());
//        MapsInitializer.initialize(getContext());


        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        latitude = -7.940346;
        longitude = 112.621441;

        final Marker[] mark = new Marker[1];
        mark[0] = googleMap.addMarker(new MarkerOptions().position(new LatLng(-7.940346,112.621441)).title("Rumahku").snippet("Ini rumahku"));
        final CameraPosition[] lokasi = {CameraPosition.builder().target(new LatLng( -7.940346, 112.621441)).zoom(5).bearing(0).tilt(0).build()};
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(lokasi[0]));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mark[0].remove();
                mark[0] = googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("Kosku").snippet("Ini kosku"));
//

                latitude = latLng.latitude;
                longitude = latLng.longitude;


        String msg1 = latitude + ", "+longitude;
                String a =String.valueOf(latitude);
                String b =String.valueOf(longitude);

                System.out.println(msg1);







//          Toast.makeText(this, msg1, Toast.LENGTH_LONG).show();

            }
        });


    }


    @Override
    public void onClick(View v) {
        if (v == buttonCurrent) {
            getCurrentLocation();
            //moveMap();

        }
    }




    @Override
    public void onConnected(Bundle bundle) {
//        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        //Clearing all the markers
        mMap.clear();

     String msg = latitude + ", "+longitude;
        //Adding a new marker to the current pressed position we are also making the draggable true
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //Getting the coordinates
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //Moving the map
        //moveMap();
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    // Getting current location
    private void getCurrentLocation() {
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            //moving the map to location
//            moveMap();
        }
    }
//    private void moveMap() {
//        //String to display current latitude and longitude
//        String msg = latitude + ", "+longitude;
//
//        //Creating a LatLng Object to store Coordinates
//        LatLng latLng = new LatLng(latitude, longitude);
//
//        //Adding marker to map
//        mMap.addMarker(new MarkerOptions()
//                .position(latLng) //setting position
//                .draggable(true) //Making the marker draggable
//                .title("Ini kos nya")); //Adding a title
//
//        //Moving the camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//
//        //Animating the camera
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//
//        //Displaying current coordinates in toast
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
//    }



    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}

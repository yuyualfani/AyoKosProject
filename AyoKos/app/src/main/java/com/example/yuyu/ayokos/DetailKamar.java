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
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailKamar extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        View.OnClickListener {

    private TextView namakos,stock,harga;

    private ImageView foto;
    private Button btnSavekamar, logout,detail;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private Uri uri;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private StorageReference imageRef,storageRef;
    UploadTask uploadTask;

    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    DatabaseReference dataKamar;


    private static final int SELECT_FOTO = 100;
    private FirebaseDatabase database;






    private GoogleMap mMap;
    private double longitude;
    private double latitude;

    private double longitude2;
    private double latitude2;



    //Buttons
    private ImageButton buttonSave;
    private ImageButton buttonCurrent;
    private ImageButton buttonView;


    //Google ApiClient
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.lihatmap);
        mapFragment.getMapAsync(this);

        //Initializing googleapi client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //Initializing views and adding onclick listeners
        buttonSave = (ImageButton) findViewById(R.id.buttonSave);
        namakos =( TextView) findViewById(R.id.namakosD);
        harga = ( TextView) findViewById(R.id.harga);
        stock = ( TextView) findViewById(R.id.stock);
        detail = (Button) findViewById(R.id.petunjuk);
        foto = (ImageView) findViewById(R.id.fotokamar);

//        buttonCurrent = (ImageButton) findViewById(R.id.buttonCurrent);
//        buttonView = (ImageButton) findViewById(R.id.buttonView);

//        buttonSave.setOnClickListener(this);
//        buttonCurrent.setOnClickListener(this);
//        buttonView.setOnClickListener(this);

//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              Intent i = new Intent(DetailKamar.this, MapsActivity.class);
//                Bundle bs = new Bundle();
//                bs.putString("kamarId",bs.getString("kamarId"));
//                bs.putString("lattt",bs.getString("lattt"));
//                bs.putString("longg",bs.getString("longg"));
//                i.putExtras(bs);
//                startActivity(i);
//
//            }
//        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bs = getIntent().getExtras();

                Intent i= new Intent(DetailKamar.this, InsertPemesanan.class);

                Bundle b = new Bundle();
                b.putString("kamarId",bs.getString("kamarId"));
                b.putString("nama",bs.getString("nama"));
                b.putString("namapemilik",bs.getString("namapemilik"));
                b.putString("harga",bs.getString("harga"));

                i.putExtras(b);
                startActivity(i);

            }
        });


        dataKamar = FirebaseDatabase.getInstance().getReference("TABLE_KAMAR");
         Bundle bbs = getIntent().getExtras();
//        System.out.println(bbs.getString("kamarId") + " kjucfytdryfkhjkh");

        namakos.setText(bbs.getString("nama"));
        stock.setText(bbs.getString("stock"));
        harga.setText(bbs.getString("harga"));

        Bundle bs = getIntent().getExtras();
        String foto = bs.getString("foto");

        ImageView im = (ImageView) findViewById(R.id.fotokamar);

        loadImageFromUrl(foto, im);

        latitude2 = Double.parseDouble(bbs.getString("lattt"));
        longitude2 = Double.parseDouble(bbs.getString("longg"));










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

    public DetailKamar() {
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

                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        latitude = -7.9456286;
        longitude = 112.6170078;

        final Marker[] mark = new Marker[1];
        mark[0] = googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude2,longitude2)).title("Rumahku").snippet("Ini rumahku"));
        final CameraPosition[] lokasi = {CameraPosition.builder().target(new LatLng(latitude2,longitude2)).zoom(5).bearing(0).tilt(0).build()};
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(lokasi[0]));




    }




    @Override
    public void onClick(View v) {
        if (v == buttonCurrent) {
         //
            //   getCurrentLocation();
            //moveMap();

        }
    }





    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (LocationListener) this);
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
           // moveMap();
        }
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
}

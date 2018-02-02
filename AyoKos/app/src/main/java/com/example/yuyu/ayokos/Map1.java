////package com.example.yuyu.ayokos;
////
////import android.*;
////import android.Manifest;
////import android.content.Context;
////import android.content.pm.PackageManager;
////import android.location.Location;
////import android.support.v4.app.DialogFragment;
////import android.location.LocationListener;
////import android.location.LocationManager;
////import android.os.Build;
////import android.support.annotation.NonNull;
////import android.support.annotation.RequiresApi;
////import android.support.v4.app.ActivityCompat;
////import android.support.v4.app.FragmentActivity;
////import android.os.Bundle;
////import android.support.v4.content.ContextCompat;
////import android.util.Log;
////import android.view.Menu;
////import android.view.MenuItem;
////import android.widget.Toast;
////
////import com.example.yuyu.ayokos.model.Kamar;
////
////import com.google.android.gms.maps.CameraUpdateFactory;
////import com.google.android.gms.maps.GoogleMap;
////import com.google.android.gms.maps.OnMapReadyCallback;
////import com.google.android.gms.maps.SupportMapFragment;
////import com.google.android.gms.maps.model.LatLng;
////import com.google.android.gms.maps.model.MarkerOptions;
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////
////
////
////
////public class Map1 extends FragmentActivity implements
////        OnMapReadyCallback,LocationListener,ActivityCompat.OnRequestPermissionsResultCallback,
////        Map2.OnAddMarker
////{
////
////    private LocationManager lm;
////    private Location location;
////    private double longitude = -25.429675;
////    private double latitude = -49.271870;
////    private FirebaseDatabase database;
////
////
////    private static final int REQUEST_PERMISSION=1;
////
////    private GoogleMap map;
////
////  //  private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
////    private static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
////
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_map1);
////        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
////
////        database = FirebaseDatabase.getInstance();
////
////        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
////                .findFragmentById(R.id.map);
////        mapFragment.getMapAsync(this);
////
////        initMaps();
////    }
////
////
////
////    private void initMaps() {
////
////        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
////               != PackageManager.PERMISSION_GRANTED
////               || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
////               !=   PackageManager.PERMISSION_GRANTED
////
////        )
////       {
////           requestPermissions();
////       }
////       else
////       {
////         lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
////           location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
////           lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 60000, this );
////
////       }
////    }
////
//////    LocationListener myLocationListener= new LocationListener() {
//////        @Override
//////        public void onLocationChanged(Location location) {
//////            Log.d("dj","on location changed: "+location.getLatitude()+" & "+location.getLongitude());
//////          //  toastLocation(location);
//////        }
//////
//////        @Override
//////        public void onStatusChanged(String provider, int status, Bundle extras) {
//////
//////        }
//////
//////        @Override
//////        public void onProviderEnabled(String provider) {
//////
//////        }
//////
//////        @Override
//////        public void onProviderDisabled(String provider) {
//////
//////        }
//////    };
////
//////    private void toastLocation(final Kamar location){
//////
//////        Handler handler = new Handler(Looper.getMainLooper());
//////
//////        handler.post(new Runnable() {
//////
//////            public Context getApplicationContext() {
//////                return applicationContext;
//////            }
//////
//////            @Override
//////            public void run() {
//////                Toast.makeText(this.getApplicationContext(),"Location latitude: "+ location.getLatitude()
//////                        +"longitude: "+location.getLongitude(), Toast.LENGTH_SHORT).show();
//////            }
//////        });
////    private void requestPermissions() {
////
////        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)
////                || ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION )
////                )
////        {
////            Toast.makeText(this, "berhaso", Toast.LENGTH_LONG).show();
////        }
////        else
////        {
////            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION);
////        }
////    }
////
////
////    /**
////     * Manipulates the map once available.
////     * This callback is triggered when the map is ready to be used.
////     * This is where we can add markers or lines, add listeners or move the camera. In this case,
////     * we just add a marker near Sydney, Australia.
////     * If Google Play services is not installed on the device, the user will be prompted to install
////     * it inside the SupportMapFragment. This method will only be triggered once the user has
////     * installed Google Play services and returned to the app.
////     */
////    @Override
////    public void onMapReady(GoogleMap map) {
////
////        this.map = map;
////        if(lm != null)
////        {
////            if(location != null)
////            {
////                latitude = location.getLatitude();
////                longitude = location.getLongitude();
////            }
////        }
////
////        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
////                == PackageManager.PERMISSION_GRANTED
////                || ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
////                ==   PackageManager.PERMISSION_GRANTED
////
////                )
////        {
////            map.setMyLocationEnabled(true);
////        }
////            map.setTrafficEnabled(true);
////
////      map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude),11));
////        LoadMarker();
////
////
////
//////        mMap = map;
////
////        // Add a marker in Sydney and move the camera
////        LatLng sydney = new LatLng(-34, 151);
////        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
////    }
////
//////    @Override
//////    public void onLocationChanged(Location location) {
//////
//////    }
////    public void LoadMarker()
////    {
////        DatabaseReference kamar = database.getReference("TABLE_KAMAR");
////        kamar.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                Iterable<DataSnapshot>dataSnapshots = dataSnapshot.getChildren();
////                map.clear();
////                for(DataSnapshot dataSnapshot1 : dataSnapshots)
////                {
////                    Kamar kamar = dataSnapshot1.getValue(Kamar.class);
////                    map.addMarker(new MarkerOptions().position(new LatLng(kamar.getLatitude(),kamar.getLongitude())).title(kamar.getNama()));
////
////                }
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////
////        switch(requestCode)
////        {
////            case REQUEST_PERMISSION:
////            {
////                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
////                {
////                    Toast.makeText(this,"Autojkhb", Toast.LENGTH_LONG).show();
////                    initMaps();
////                }
////                else
////                {
////                    Toast.makeText(this,"Autojkhbddddddddd", Toast.LENGTH_LONG).show();
////
////                }
////                return;
////            }
////        }
////
////
////    }
////
////
////
//////    @Override
//////    public boolean onCreateOptionsMenu(Menu menu) {
////////        return super.onCreateOptionsMenu(menu);
//////       // getMenuInflater().inflate();
//////    }
////
////
//////    @Override
//////    public boolean onOptionsItemSelected(MenuItem item) {
////////        return super.onOptionsItemSelected(item);
//////        switch(item.getItemId())
//////        {
//////
//////        }
//////    }
////    public void onAddMarker()
////    {
////        LoadMarker();
////    }
////
////    @Override
////    public void onLocationChanged(Location location) {
////
////    }
////
////    @Override
////    public void onStatusChanged(String s, int i, Bundle bundle) {
////
////    }
////
////    @Override
////    public void onProviderEnabled(String s) {
////
////    }
////
////    @Override
////    public void onProviderDisabled(String s) {
////
////    }
////
////    public void addPin()
////    {
////        Map2 map = Map2.getInstance(this);
////
////        map.show(getSupportFragmentManager(),"map");
////    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu,menu);
////        return true;
////
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        switch(item.getItemId())
////        {
////            case R.id.addMenu:
////                addPin();
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
////    }
////}
////
////
////
////
////
////
////
////
////
////
////
//
//
//
//
//package com.example.yuyu.ayokos;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.Manifest;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.example.yuyu.ayokos.model.Kamar;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//
//
//public class Map1 extends AppCompatActivity implements OnMapReadyCallback,LocationListener,ActivityCompat.OnRequestPermissionsResultCallback,Map2.OnAddMarker {
//
//    private LocationManager lm;
//    private Location location;
//    private double longitude = -25.429675;
//    private double latitude = -49.271870;
//
//    private FirebaseDatabase database;
//
//    private static final int REQUEST_PERMISSION = 1;
//
//    private GoogleMap map;
//
//
//    private static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map1);
//
//        database = FirebaseDatabase.getInstance();
//
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        initMaps();
//
//
//
//    }
//
//    public void initMaps(){
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            requestPermissions();
//
//        } else {
//            lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 60000, this);
//        }
//    }
//
//
//    @Override
//    public void onMapReady(GoogleMap map) {
//        this.map = map;
//        if (lm != null) {
//            if (location != null) {
//                latitude = location.getLatitude();
//                longitude = location.getLongitude();
//
//            }
//        }
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            map.setMyLocationEnabled(true);
//        }
//        map.setTrafficEnabled(true);
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11));
//
//        loadMarker();
//
//        LatLng sydney = new LatLng(-34, 151);
//       map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//       map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//   }
//
//    public void addPin(){
//        Map2 localDialog = Map2.getInstance(this);
//        localDialog.show(getSupportFragmentManager(), "localDialog");
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.addMenu:
//                addPin();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//
//
//    @Override
//    public void onLocationChanged(Location arg0) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String arg0) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String arg0) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_PERMISSION: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this,"Autorizado",Toast.LENGTH_SHORT).show();
//                    initMaps();
//
//                } else {
//                    Toast.makeText(this,"Permissão negada",Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//
//        }
//    }
//
//    private void requestPermissions() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.ACCESS_COARSE_LOCATION)
//                || ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//
//            Toast.makeText(this,"Permissão negada",Toast.LENGTH_SHORT).show();
//        } else {
//            ActivityCompat.requestPermissions(this,PERMISSIONS , REQUEST_PERMISSION);
//        }
//    }
//
//    public void loadMarker(){
//        DatabaseReference kamar = database.getReference("TABLE_KAMAR");
//
//        kamar.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
//                map.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshots) {
//                    Kamar kamar = dataSnapshot1.getValue(Kamar.class);
//                    map.addMarker(new MarkerOptions().position(new LatLng(kamar.getLatitude(), kamar.getLongitude())).title(kamar.getNama()));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
//    }
//
//    @Override
//    public void onAddMarker() {
//        loadMarker();
//    }
//}
//
//


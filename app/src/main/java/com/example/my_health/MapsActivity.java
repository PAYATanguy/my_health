package com.example.my_health;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;


public class MapsActivity extends MenuActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;


        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            // Dr Arnaud Berger Perrin
            LatLng doc1 = new LatLng(45.7797156, 4.8038001);
            googleMap.addMarker(new MarkerOptions().position(doc1)
                    .title("Dr Arnaud Berger Perrin")
                    .snippet("14 Rue du Chapeau Rouge, 69009 Lyon"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc1));

            // Dr Forestier Benoit
            LatLng doc2 = new LatLng(45.77264412268098, 4.80730496987383);
            googleMap.addMarker(new MarkerOptions().position(doc2)
                    .title("Dr Forestier Benoit")
                    .snippet("1 Rue Constantine, 69001 Lyon"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc2));

            // Docteur Long Pierre Olivier
            LatLng doc3 = new LatLng(45.77336842268097, 4.808192766522696);
            googleMap.addMarker(new MarkerOptions().position(doc3)
                    .title("Docteur Long Pierre Olivier")
                    .snippet("3 Rue Saint-Pierre de Vaise, 69009 Lyon"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc3));

            // Docteur Bruno Dubessy
            LatLng doc4 = new LatLng(45.76100792268097, 4.8271655236937026);
            googleMap.addMarker(new MarkerOptions().position(doc4)
                    .title("Docteur Bruno Dubessy")
                    .snippet("6 Rue Saint-Etienne, 69005 Lyon"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc4));

            // Dr Petit Isabelle
            LatLng doc5 = new LatLng(45.778608722680964, 4.8075880422734665);
            googleMap.addMarker(new MarkerOptions().position(doc5)
                    .title("Dr Petit Isabelle")
                    .snippet("2 Rue Masaryk, 69009 Lyon"));

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc5));

            // Benoit Béatrice
            LatLng doc6 = new LatLng(45.750749222680966, 4.799385571114907);
            googleMap.addMarker(new MarkerOptions().position(doc6)
                    .title("Benoit Béatrice")
                    .snippet("86 Rue Commandant Charcot, 69005 Lyon"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc6));

            // Dr Franck Raimondo
            LatLng doc7 = new LatLng(45.75169042268097, 4.824188266765248);
            googleMap.addMarker(new MarkerOptions().position(doc7)
                    .title("Dr Franck Raimondo")
                    .snippet("42 Rue Vaubecour, 69002 Lyon"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(doc7));

        }
    }
        /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() + marker.getSnippet() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getLocationPermission();
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();

                        assert currentLocation != null;
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())
                        );

                    }else{
                        Log.d(TAG, "onComplete: current location is null");
                        Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MapsActivity.DEFAULT_ZOOM));
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }


}



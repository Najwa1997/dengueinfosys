package com.example.dengueinfosys;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;
import com.example.dengueinfosys.Interface.IOnLoadLocationListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryDataEventListener;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class HotspotsActivity extends FragmentActivity implements OnMapReadyCallback, IOnLoadLocationListener {
    private LocationRequest locationRequest;
    private GoogleMap mMap;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentUser;
    private DatabaseReference myLocationRef;
    private DatabaseReference myCity;
    private GeoFire geoFire;
    private List<LatLng> dangerousArea;
    private IOnLoadLocationListener listener;
    private Location lastLocation;
    private GeoQuery geoQuery;
    static HotspotsActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspots);


        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        buildLocationRequest();
                        buildLocationCallback();

                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(HotspotsActivity.this);


                        initArea();
                        settingGeoFire();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
        MapsInitializer.initialize(HotspotsActivity.this);

    }



    private void initArea() {
        myCity=FirebaseDatabase.getInstance().getReference("DengueHotspotArea")
                .child("City");
        listener = this;
  /*    myCity.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<MyLatLng> latLngList=new ArrayList<>();
                        for(DataSnapshot locationSnapShot:dataSnapshot.getChildren()){
                            MyLatLng latLng=locationSnapShot.getValue(MyLatLng.class);
                            latLngList.add(latLng);
                        }
                        listener.onLoadLocaionSuccess(latLngList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        listener.onLoadLocationFailed(databaseError.getMessage());
                    }
                });*/
        myCity.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<MyLatLng> latLngList=new ArrayList<>();
                for(DataSnapshot locationSnapShot:dataSnapshot.getChildren()){
                    MyLatLng latLng=locationSnapShot.getValue(MyLatLng.class);
                    latLngList.add(latLng);
                }
                listener.onLoadLocationSuccess(latLngList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onLoadLocationFailed(databaseError.getMessage());

            }
        });
/*
        dangerousArea = new ArrayList<>();
        dangerousArea.add(new LatLng(17.355, 78.563));
        dangerousArea.add(new LatLng(17.355, 78.663));
        dangerousArea.add(new LatLng(17.355, 78.763));
/*
        FirebaseDatabase.getInstance().getReference("DangerousArea")
                .child("MyAreas")
                .setValue(dangerousArea)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MapsActivity.this,"Updated!",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MapsActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    private void addUserMarker() {
        geoFire.setLocation("You", new GeoLocation(lastLocation.getLatitude(), lastLocation.getLongitude()), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (currentUser != null) currentUser.remove();
                currentUser = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lastLocation.getLatitude(),
                                lastLocation.getLongitude()))
                        .title("You"));

                mMap.animateCamera(CameraUpdateFactory
                        .newLatLngZoom(currentUser.getPosition(), 12.0f));

            }
        });
    }

    private void settingGeoFire() {
        myLocationRef = FirebaseDatabase.getInstance().getReference("MyLocation");
        geoFire = new GeoFire(myLocationRef);
    }

    private void buildLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(final LocationResult locationResult) {
                if (mMap != null) {
                    lastLocation=locationResult.getLastLocation();
                    addUserMarker();
                }
            }
        };

    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (fusedLocationProviderClient != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        // Add circle
        addCircleArea();


    }

    private void addCircleArea() {
        if(geoQuery!=null){
            geoQuery.removeGeoQueryEventListener((GeoQueryDataEventListener) this);
            geoQuery.removeAllListeners();}
        for (LatLng latLag : dangerousArea) {
            mMap.addCircle(new CircleOptions().center(latLag)
                    .radius(500)
                    .strokeColor(Color.RED)
                    .fillColor(0x220000FF)
                    .strokeWidth(5.0f));


            GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(latLag.latitude, latLag.longitude), 0.5f);
            geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                @Override
                public void onKeyEntered(String key, GeoLocation location) {
                    sendNotification("DengueHotspot", String.format("%s entered in affected area", key));
                }

                @Override
                public void onKeyExited(String key) {
                    sendNotification("DengueHotspot", String.format("%s left affected area", key));
                }

                @Override
                public void onKeyMoved(String key, GeoLocation location) {
                    sendNotification("DengueHotspot",String.format("%s move within the affected area",key));
                }

                @Override
                public void onGeoQueryReady() {

                }

                @Override
                public void onGeoQueryError(DatabaseError error) {
                    //Toast.makeText(this,""+error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        super.onStop();
    }



    private void sendNotification(String title, String content) {
        String NOTIFICATION_CHANNEL_ID = "dengue_multiple_location";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Channel Description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000, 500});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification notification = builder.build();
        notificationManager.notify(new Random().nextInt(), notification);
    }

    @Override
    public void onLoadLocationSuccess(List<MyLatLng> latLngs) {
        dangerousArea=new ArrayList<>();
        for(MyLatLng myLatlng:latLngs){
            LatLng convert=new LatLng(myLatlng.getLatitude(),myLatlng.getLongitude());
            dangerousArea.add(convert);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(HotspotsActivity.this);


        if(mMap!=null){
            mMap.clear();
            addUserMarker();

            addCircleArea();
        }
    }

    @Override
    public void onLoadLocationFailed(String message) {
        Toast.makeText(this,""+message,Toast.LENGTH_SHORT).show();
    }


}
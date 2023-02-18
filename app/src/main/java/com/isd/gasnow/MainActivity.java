package com.isd.gasnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.snackbar.Snackbar;
import com.isd.gasnow.Database.SessionManager;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    RelativeLayout layout;
    boolean isPermissionGranted;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);
        mapView = findViewById(R.id.mapView);
        checkMyPermission();

        if(isPermissionGranted)
        {
            mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);
        }


    }

    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Snackbar.make(layout,"Permission Granted",Snackbar.LENGTH_LONG).show();
                isPermissionGranted = true;

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getPackageName(),"");
                intent.setData(uri);

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
}
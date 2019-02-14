package com.walletbus.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.walletbus.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


//        // Add a marker in Sydney and move the camera
          LatLng TerminalChoama = new LatLng(-2.5193034,-44.2474748);
//        mMap.addMarker(new MarkerOptions().position(Terminal).title("Terminal de Integração Cohama/Vinhais"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(Terminal));
          LatLng TerminalCohab = new LatLng(-2.5435542, -44.2170123);
//        mMap.addMarker(new MarkerOptions().position(TerminalCh).title("Terminal de Integração - Cohab/Cohatrac"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(TerminalCh));
          LatLng TerminalSc = new LatLng(-2.5696598,-44.2253782);
          LatLng TerminalPg = new LatLng(-2.5318048,-44.3076583);

        /*Terminal de Integração Cohama/Vinhais*/
        mMap.addMarker(
                new MarkerOptions()
                .position(TerminalChoama)
                .title("Terminal de Integração Cohama/Vinhais")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalChoama,14)
        );
        /*----------------------------------------------------------*/
        /*Terminal de Integração - Cohab/Cohatracs*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalCohab)
                        .title("Terminal de Integração - Cohab/Cohatrac")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab,14)
        );
        /*----------------------------------------------------------*/
        /*Terminal de Integração São Cristovão*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalSc)
                        .title("Terminal de Integração São Cristovão")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab,14)
        );
        /*----------------------------------------------------------*/
        /*Terminal de Integração Praia Grande*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalPg)
                        .title("Terminal de Integração Praia Grande")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab,14)
        );

        /*----------------------------------------------------------*/



    }
}

package com.walletbus.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.walletbus.R;
import com.walletbus.helper.Permissoes;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private LocationManager locatationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //VALIDAR PERMISSOES
        Permissoes.validarPermissoes(permissoes, this, 1);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        recuperarLocalizacaoUsuario();

//        // Add a marker in Sydney and move the camera
        LatLng TerminalChoama = new LatLng(-2.5193034, -44.2474748);
//        mMap.addMarker(new MarkerOptions().position(Terminal).title("Terminal de Integração Cohama/Vinhais"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(Terminal));
        LatLng TerminalCohab = new LatLng(-2.5435542, -44.2170123);
//        mMap.addMarker(new MarkerOptions().position(TerminalCh).title("Terminal de Integração - Cohab/Cohatrac"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(TerminalCh));
        LatLng TerminalSc = new LatLng(-2.5696598, -44.2253782);
        LatLng TerminalPg = new LatLng(-2.5318048, -44.3076583);
        //UEMA
        LatLng TerminalUe = new LatLng(-2.5773386, -44.2096066);
        //UFMA
        LatLng TerminalUf = new LatLng(-2.5583842, -44.3091818);
        //TERMINAL INSDUSTRIAL
        LatLng TerminalTi = new LatLng(-2.6385781, -44.2698317);
        //CENTRAL DO ESTUDANTE
        LatLng TerminalCe = new LatLng(-2.5402268, -44.2772221);
        //SINDICATO
        LatLng TerminalSi = new LatLng(-2.5333915, -44.2943362);



        /*Terminal de Integração Cohama/Vinhais*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalChoama)
                        .title("Terminal de Integração Cohama/Vinhais")
                        .snippet("Funcionamento: 6h30 às 19h")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalChoama, 18)
        );
        /*----------------------------------------------------------*/
        /*Terminal de Integração - Cohab/Cohatracs*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalCohab)
                        .title("Terminal de Integração - Cohab/Cohatrac")
                        .snippet("Funcionamento: 6h30 às 19h")

        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );
        /*----------------------------------------------------------*/
        /*Terminal de Integração São Cristovão*/
        mMap.addMarker(

                new MarkerOptions()
                        .position(TerminalSc)
                        .title("Terminal de Integração São Cristovão")
                        .snippet("Funcionamento: 6h30 às 19h")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );
        /*----------------------------------------------------------*/
        /*Terminal de Integração Praia Grande*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalPg)
                        .title("Terminal de Integração Praia Grande")
                        .snippet("Funcionamento: 6h30 às 19h")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );

        /*----------------------------------------------------------*/

        /*------------------------UEMA----------------------------------*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalUe)
                        .title("UEMA - Universidade Estadual do Maranhão")
                        .snippet("Funcionamento: 19h e 20h ")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );

        /*----------------------------------------------------------*/

        /*------------------------UFMA----------------------------------*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalUf)
                        .title("Universidade Federal do Maranhão")
                        .snippet("Funcionamento: 19h e 20h ")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );

        /*----------------------------------------------------------*/

        /*------------------------TERMINAL INDUSTRIAL----------------------------------*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalTi)
                        .title("Terminal de Integração Distrito Industrial, São Luís MA")
                        .snippet("Funcionamento: 19h e 19h")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );

        /*----------------------------------------------------------*/

        /*------------------------CENTRAL DO ESTUDANTE----------------------------------*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalCe)
                        .title("Central do Estudante")
                        .snippet("Funcionamento: 7h às 17h")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );

        /*----------------------------------------------------------*/
        /*------------------------SINDICATO ES----------------------------------*/
        mMap.addMarker(
                new MarkerOptions()
                        .position(TerminalSi)
                        .title("Sindicato das Empresas de Transportes de Passageiros de São Luis")
                        .snippet("Funcionamento: 6h30 às 19h")
        );
        mMap.moveCamera(//2.0 21.0

                CameraUpdateFactory.newLatLngZoom(TerminalCohab, 18)
        );


    }

    private void recuperarLocalizacaoUsuario(){


        //Objeto responsável por gerenciar a localização do usuário
        locatationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d("Localização", "onLocationChanged: " + location.toString());

                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();
                LatLng localUusuario = new LatLng(latitude, longitude);

                mMap.addMarker(
                        new MarkerOptions()
                                .position(localUusuario)
                                .title("Meu Local")
                                //

                );
                mMap.moveCamera(//2.0 21.0

                        CameraUpdateFactory.newLatLngZoom(localUusuario, 15)
                );

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        //Recuperar localização do usuario
        /*
         * 1) Provedor da localização
         * 2) Tempo mínimo entre atualizacões de localização (milesegundos)
         * 3) Distancia mínima entre atualizacões de localização (metros)
         * 4) Location listener (para recebermos as atualizações)
         * */

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling

            locatationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    10,
                    locationListener

            );

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults) {

            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                //ALERTA
                alertaValidacaoPermissao();
            } else if (permissaoResultado == PackageManager.PERMISSION_GRANTED) {

                //Recuperar localização do usuario
                /*
                 * 1) Provedor da localização
                 * 2) Tempo mínimo entre atualizacões de localização (milesegundos)
                 * 3) Distancia mínima entre atualizacões de localização (metros)
                 * 4) Location listener (para recebermos as atualizações)
                 * */

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    locatationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            10000,
                            10,
                            locationListener

                    );

                    }
                  }

            }
        }
        private void alertaValidacaoPermissao(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permissões Negadas");
            builder.setMessage("Para utilizar o app é necessario aceitar as permissões!");
            builder.setCancelable(false);
            builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }
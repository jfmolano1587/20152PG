package com.example.jfm.myapplication;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.location.Location;
import android.content.Context;
import android.location.LocationManager;

public class MainActivity extends Activity {

    private Button btnInicio;
    private LocationManager locationManager;
    private WifiManager wifiMgr;
    private WifiInfo wifiInfo;
    private DhcpInfo dhcpInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Informacion WIFI
        wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
        //wifiInfo = wifiMgr.getConnectionInfo();
        //int ip = wifiInfo.getIpAddress();
        //String ipAddress = Formatter.formatIpAddress(ip);
        //Informacion WIFI
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        addListenerOnButton();
    }

    public void medicion()
    {
        //WIFI
        wifiInfo = wifiMgr.getConnectionInfo();
        dhcpInfo = wifiMgr.getDhcpInfo();
        int ip = wifiInfo.getIpAddress();
        String mac = wifiInfo.getMacAddress();
        int netId = wifiInfo.getNetworkId();
        int dns1 = dhcpInfo.dns1;
        int dns2 = dhcpInfo.dns2;
        int contents = dhcpInfo.describeContents();
        int gateway = dhcpInfo.gateway;
        int ipdhcp = dhcpInfo.ipAddress;
        int leaseduration = dhcpInfo.leaseDuration;
        int netmask = dhcpInfo.netmask;
        int servaddress = dhcpInfo.serverAddress;
        String ipAddress = Formatter.formatIpAddress(ip);
        String netIdString = Formatter.formatIpAddress(netId);
        System.out.println("- - - - - IP - - - - -");
        System.out.println(ipAddress);
        System.out.println("- - - - - MAC - - - - -");
        System.out.println(mac);
        System.out.println("- - - - - ID de Net - - - - -");
        System.out.println(netIdString);
        System.out.println("- - - - - IP - - - - -");
        System.out.println(ipAddress);
        System.out.println("- - - - - MAC - - - - -");
        System.out.println(mac);
        System.out.println("- - - - - ID de Net - - - - -");
        System.out.println(netIdString);
        //GPS
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        System.out.println("GPS");
        if(location!=null) {
            if (location.hasAltitude()) {
                double altitud = location.getAltitude();
                System.out.println("altitud");
                System.out.println(altitud);
            }
            if (location.hasSpeed()) {
                double velocidad = location.getSpeed();
                System.out.println("velocidad");
                System.out.println(velocidad);
            }
            if (true) {
                double longitud = location.getLongitude();
                System.out.println("longitud");
                System.out.println(longitud);
            }
            if (true) {
                double latitud = location.getLatitude();
                System.out.println("latitud");
                System.out.println(latitud);
            }
        }
    }

    public void addListenerOnButton() {
        btnInicio = (Button) findViewById(R.id.btnDisplay);

        btnInicio.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,
                        "En click", Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    public void run() {
                        medicion();
                    }
                }).start();

            }

        });

    }
}
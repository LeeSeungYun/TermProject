package com.example.leeseungyun.mylogger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * Created by Lee Seung Yun on 2016-11-10.
 */
public class MapView extends FragmentActivity {
    GoogleMap gmap;
    MarkerOptions marker;


    LatLng location = new LatLng(37.559603, 126.982203);
    CameraPosition cp = new CameraPosition.Builder().target(location).zoom(11).build();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_map);

        DataList datall = new DataList();

        gmap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

        for(int i = 0 ; i < datall.arr_str.size(); i++){
            LatLng pinmark = new LatLng(datall.arr_lat.get(i),datall.arr_lon.get(i));
            marker = new MarkerOptions().position(pinmark).title(datall.arr_str.get(i));
            gmap.addMarker(marker);
        }
    }
}

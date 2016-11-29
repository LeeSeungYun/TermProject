package com.example.leeseungyun.mylogger2;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    LatLng moveLocation = new LatLng(37.559603, 126.982203);
    LatLng pinLocation;
    CameraPosition cp = new CameraPosition.Builder().target(moveLocation).zoom(11).build();


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_map);

        DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);

        MainActivity.arr_lat.clear();
        MainActivity.arr_lon.clear();
        MainActivity.arr_evt.clear();
        MainActivity.arr_cat.clear();

        gmap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        dbManager.getMarkerLocation();

        for(int i = 0 ; i < MainActivity.arr_lat.size() ; i++){
            pinLocation = new LatLng(MainActivity.arr_lat.get(i),MainActivity.arr_lon.get(i));
            switch(MainActivity.arr_cat.get(i)){
                case "공부":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    break;
                case "데이트":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    break;
                case "노동":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    break;
                case "이동중":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    break;
                case "식사":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                    break;
                case "운동":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    break;
                case "여행":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    break;
                case "기타":
                    marker = new MarkerOptions().position(pinLocation).title(MainActivity.arr_evt.get(i)).icon(
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    break;
            }
            gmap.addMarker(marker);
        }


    }
}

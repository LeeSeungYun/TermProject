package com.example.leeseungyun.mylogger2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Lee Seung Yun on 2016-11-15.
 */
public class Statistics extends FragmentActivity{


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_statistics);

        DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);

        TextView statisticContent = (TextView) findViewById(R.id.eventContents);
        String str = dbManager.printData();

        statisticContent.setText(str);

    }

}

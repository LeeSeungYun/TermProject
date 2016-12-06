package com.example.leeseungyun.mylogger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
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

        final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);

        final TextView statisticContent = (TextView) findViewById(R.id.eventContents);
        final Spinner selectitem = (Spinner) findViewById(R.id.selectSpinner);
        Button searchBtn = (Button) findViewById(R.id.statisticSearch);
        Button searchmapBtn = (Button) findViewById(R.id.statisticMap);

        final String str = dbManager.printData();
        statisticContent.setText(str);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String Item = selectitem.getSelectedItem().toString(); //검색하려고 선택한 카테고리
                if(Item.equals("전체")){
                    statisticContent.setText(str);
                }else{
                    statisticContent.setText(dbManager.select(Item));
                }
            }
       });

        searchmapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),MapView.class);
                it.putExtra("category",selectitem.getSelectedItem().toString());
                startActivity(it);
            }
        });

    }

}

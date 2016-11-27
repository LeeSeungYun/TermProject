package com.example.leeseungyun.mylogger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Lee Seung Yun on 2016-11-24.
 */
public class EventLog extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlog);

        final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        final EditText content = (EditText) findViewById(R.id.eventText);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button cameraBtn = (Button) findViewById(R.id.cameraBtn);
        Button inputBtn = (Button) findViewById(R.id.logBtn);
        Button cancelBtn = (Button) findViewById(R.id.backBtn);

        inputBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Date date = new Date(calendarView.getDate());
                StringTokenizer strToken = new StringTokenizer(date.toLocaleString()," .");
                String year = strToken.nextToken(); //년
                String month = strToken.nextToken(); //월
                String day = strToken.nextToken(); //일
                String text = content.getText().toString(); //이벤트 내용
                String category = spinner.getSelectedItem().toString(); //분류
                dbManager.insert("insert into EventDB values(null,"+String.valueOf(0.00)+","+String.valueOf(0.000)+","+year+","+month+","+day+",'"+category+"','"+text+"');");
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });


    }
}

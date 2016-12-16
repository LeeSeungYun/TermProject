package com.example.leeseungyun.mylogger2;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

/**
 * Created by Lee Seung Yun on 2016-11-24.
 */
public class EventLog extends AppCompatActivity {
    String imgPath = "aaa";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlog);

        final DBManager dbManager = new DBManager(getApplicationContext(),"Logger.db",null,1);
        final GetGPS getGPS = new GetGPS(this);

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
                getGPS.getLocation();
                double lat = getGPS.getLatitude(); //위도
                double lon = getGPS.getLongitude(); //경도
                String year = strToken.nextToken(); //년
                String month = strToken.nextToken(); //월
                String day = strToken.nextToken(); //일
                String text = content.getText().toString(); //이벤트 내용
                String category = spinner.getSelectedItem().toString(); //분류
//                Toast.makeText(getApplicationContext(),imgPath,Toast.LENGTH_SHORT).show();
                dbManager.insert("insert into EventDB values(null,"+String.valueOf(lat)+","+String.valueOf(lon)+","+year+","+month+","+day+",'"+category+"','"+text+"');");
                finish();
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File pictureControl = savePicture();

                cameraApp.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(pictureControl));
                startActivityForResult(cameraApp,10000);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });


    }

    public File savePicture(){
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imgName = "IMG_"+timestamp;
        String path = "";

        File storage = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM), "LifeLogger/");
        if(!storage.exists()){storage.mkdirs();}
        try {
            File file = File.createTempFile(imgName, ".jpg", storage);
            imgPath = file.getAbsolutePath();
//            Toast.makeText(getApplicationContext(),imgPath,Toast.LENGTH_SHORT).show();
            File f = new File(imgPath);
            Uri contentUri = Uri.fromFile(f);
            Intent mediaScan = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScan.setData(contentUri);
            this.sendBroadcast(mediaScan);

            return file;
        }catch(Exception e){
            e.toString();
        }


        return null;
    }
}

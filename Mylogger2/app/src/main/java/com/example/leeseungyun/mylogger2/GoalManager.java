package com.example.leeseungyun.mylogger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lee Seung Yun on 2016-12-16.
 */
public class GoalManager extends AppCompatActivity {
    protected void onCreate(Bundle SavaInstanceState){
        super.onCreate(SavaInstanceState);
        setContentView(R.layout.activity_goalmanager);

        final GoalDB goalDB = new GoalDB(getApplicationContext(),"GoalManager.db",null,1);

        Button goal_1_Up = (Button)findViewById(R.id.goal_1_Up);
        Button goal_1_Down = (Button)findViewById(R.id.goal_1_Down);
        Button goal_2_Up = (Button)findViewById(R.id.goal_2_Up);
        Button goal_2_Down = (Button)findViewById(R.id.goal_2_Down);
        Button goal_3_Up = (Button)findViewById(R.id.goal_3_Up);
        Button goal_3_Down = (Button)findViewById(R.id.goal_3_Down);
        Button goal_4_Up = (Button)findViewById(R.id.goal_4_Up);
        Button goal_4_Down = (Button)findViewById(R.id.goal_4_Down);
        Button resetBtn = (Button)findViewById(R.id.goalReset);
        final TextView goal_1_Value = (TextView)findViewById(R.id.goal_1_Value);
        final TextView goal_2_Value = (TextView)findViewById(R.id.goal_2_Value);
        final TextView goal_3_Value = (TextView)findViewById(R.id.goal_3_Value);
        final TextView goal_4_Value = (TextView)findViewById(R.id.goal_4_Value);

        goal_1_Value.setText(String.valueOf(goalDB.select(0))+"kg");
        goal_2_Value.setText(String.valueOf(goalDB.select(1))+"권");
        goal_3_Value.setText(String.valueOf(goalDB.select(2))+"H");
        goal_4_Value.setText(String.valueOf(goalDB.select(3))+"H");

        goal_1_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(0)-1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 0;");
                goal_1_Value.setText(String.valueOf(goalDB.select(0))+"kg");
            }
        });

        goal_1_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(0)+1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 0;");
                goal_1_Value.setText(String.valueOf(goalDB.select(0))+"kg");
            }
        });

        goal_2_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(1)-1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 1;");
                goal_2_Value.setText(String.valueOf(goalDB.select(1))+"권");
            }
        });

        goal_2_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(1)+1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 1;");
                goal_2_Value.setText(String.valueOf(goalDB.select(1))+"권");
            }
        });

        goal_3_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(2)-1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 2;");
                goal_3_Value.setText(String.valueOf(goalDB.select(2))+"H");
            }
        });

        goal_3_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(2)+1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 2;");
                goal_3_Value.setText(String.valueOf(goalDB.select(2))+"H");
            }
        });

        goal_4_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(3)-1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 3;");
                goal_4_Value.setText(String.valueOf(goalDB.select(3))+"H");
            }
        });

        goal_4_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = goalDB.select(3)+1;
                goalDB.update("update GoalDB set goal_value = "+ temp +" where key = 3;");
                goal_4_Value.setText(String.valueOf(goalDB.select(3))+"H");
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalDB.clear();
                for(int i = 0 ; i < 4 ; i++)
                    goalDB.insert("insert into GoalDB values(null," + "0" + ","+i+");");
                goal_1_Value.setText(String.valueOf(goalDB.select(0)));
                goal_2_Value.setText(String.valueOf(goalDB.select(1)));
                goal_3_Value.setText(String.valueOf(goalDB.select(2)));
                goal_4_Value.setText(String.valueOf(goalDB.select(3)));
            }
        });
    }
}

package com.example.team_click;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlarmActivity extends AppCompatActivity {
    ImageButton alarm_add;
    EditText alarm_content;
    TimePicker timepicker;
    LinearLayout alarm_container;
    View alarmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarm_add = findViewById(R.id.alarm_add);
        timepicker = findViewById(R.id.timepicker);
        alarm_container = findViewById(R.id.alarm_container);

        alarm_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmView = View.inflate(AlarmActivity.this, R.layout.add_alarm, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(AlarmActivity.this);
                dlg.setView(alarmView);
                dlg.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarm_content = alarmView.findViewById(R.id.alarm_content);
                        String content = alarm_content.getText().toString();
                        String hour = String.format("%02d", timepicker.getHour());
                        String minute = String.format("%02d", timepicker.getMinute());

                        // 새로운 알람 레이아웃을 inflate
                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        View newAlarmPart = inflater.inflate(R.layout.alarm_part, null);

                        // 새로운 레이아웃 안에 있는 TextView들을 초기화하고 설정
                        TextView newAlarmContent = newAlarmPart.findViewById(R.id.alarm_part_content);
                        TextView newAlarmHour = newAlarmPart.findViewById(R.id.alarm_hour);
                        TextView newAlarmMinute = newAlarmPart.findViewById(R.id.alarm_minute);

                        newAlarmContent.setText(content);
                        newAlarmHour.setText(hour);
                        newAlarmMinute.setText(minute);

                        // 새로운 레이아웃을 alarm_container에 추가
                        alarm_container.addView(newAlarmPart);

                        // alarm_container를 보이게 설정
                        alarm_container.setVisibility(View.VISIBLE);
                    }
                });
                dlg.show();
            }
        });
        ImageButton alarm_cancel = findViewById(R.id.alarm_cancel);
        alarm_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

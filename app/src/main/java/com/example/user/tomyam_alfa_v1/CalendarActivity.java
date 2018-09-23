package com.example.user.tomyam_alfa_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView mCalendarView = (CalendarView)findViewById(R.id.calendar_view);

        mCalendarView.setOnDateChangeListener(new OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                Intent intent = new Intent(CalendarActivity.this, DayActivity.class);

                month = month + 1;

                String date = dayOfMonth + "." + month + "." + year;
                String tableDate = (dayOfMonth + "" + month + "" + year);
                intent.putExtra("date", date);
                intent.putExtra("tableDate", tableDate);

                startActivity(intent);
//
            }});
    }
}
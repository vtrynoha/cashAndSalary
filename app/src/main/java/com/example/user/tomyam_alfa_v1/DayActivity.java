package com.example.user.tomyam_alfa_v1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    String date, tableDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        date = getIntent().getExtras().getString("date");
        tableDate = getIntent().getExtras().getString("tableDate");

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(date);

        show();
    }

    public void change(View view) {
        Intent intent = new Intent(DayActivity.this, KasaActivity.class);

        intent.putExtra("date", date);
        intent.putExtra("tableDate", tableDate);

        startActivity(intent);
    }

    private String kasaForDay() {
        SQLiteDatabase tomYamDB1 = openOrCreateDatabase("table" + tableDate, MODE_PRIVATE, null);
        tomYamDB1.execSQL("CREATE TABLE IF NOT EXISTS table" + tableDate + " (kasaForDay String, salaryWorker1 int, " +
                "salaryWorker2 int, salaryWorker3 int, salaryWorker4 int, salaryWorker5 int, salaryWorker6 int)");
        Cursor myCursor = tomYamDB1.rawQuery("select kasaForDay, salaryWorker1, salaryWorker2, salaryWorker3, " +
                "salaryWorker4, salaryWorker5, salaryWorker6 from table" + tableDate, null);
        String kasaForDay = "";
        int salaryWorker1 = 0, salaryWorker2 = 0, salaryWorker3 = 0, salaryWorker4 = 0, salaryWorker5 = 0, salaryWorker6 = 0;

        while (myCursor.moveToNext()) {
            kasaForDay = myCursor.getString(0);
            salaryWorker1 = myCursor.getInt(1);
            salaryWorker2 = myCursor.getInt(2);
            salaryWorker3 = myCursor.getInt(3);
            salaryWorker4 = myCursor.getInt(4);
            salaryWorker5 = myCursor.getInt(5);
            salaryWorker6 = myCursor.getInt(6);
        }

        String summary = ("Каса: " + kasaForDay + "\n" + "Зарплата: ");
        summary += "\n Працівник1: " + salaryWorker1;
        summary += "\n Працівник2: " + salaryWorker2;
        summary += "\n Працівник3: " + salaryWorker3;
        summary += "\n Працівник4: " + salaryWorker4;
        summary += "\n Працівник5: " + salaryWorker5;
        summary += "\n Працівник6: " + salaryWorker6;

        myCursor.close();
        tomYamDB1.close();

        return summary;
    }

    public void show() {
        TextView kasaTextView = (TextView) findViewById(R.id.kasa_text_view);
        kasaTextView.setText(kasaForDay());
    }
}

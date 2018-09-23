package com.example.user.tomyam_alfa_v1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KasaActivity extends AppCompatActivity {

    String date, tableDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasa);

        date = getIntent().getExtras().getString("date");
        tableDate = getIntent().getExtras().getString("tableDate");

        TextView textView = (TextView) findViewById(R.id.dataTextView);
        textView.setText(date);
    }

    private String kasaForDay() {
        EditText kasaForDayText = findViewById(R.id.kasaEditView);
        String sKasaForDay = kasaForDayText.getText().toString();
        return sKasaForDay;
    }

    private int salary() {
        int salary;
        String sKasaForDay = kasaForDay();
        int kasaForDay = Integer.parseInt(sKasaForDay.trim());

        if (kasaForDay > 0 && kasaForDay <= 3000)
            salary = 200;
        else if (kasaForDay > 3000 && kasaForDay <= 4000)
            salary = 220;
        else if (kasaForDay > 4000 && kasaForDay <= 4500)
            salary = 240;
        else if (kasaForDay > 4500 && kasaForDay <= 5000)
            salary = 260;
        else if (kasaForDay > 5000 && kasaForDay <= 5500)
            salary = 280;
        else if (kasaForDay > 5500 && kasaForDay <= 6000)
            salary = 300;
        else if (kasaForDay > 6000 && kasaForDay <= 6500)
            salary = 320;
        else if (kasaForDay > 6500 && kasaForDay <= 7000)
            salary = 340;
        else if (kasaForDay > 7000 && kasaForDay <= 7500)
            salary = 360;
        else if (kasaForDay > 7500 && kasaForDay <= 8000)
            salary = 380;
        else if (kasaForDay > 8000)
            salary = 400;
        else
            salary = 0;

        return salary;
    }

    private int workerSalary(int i) {
        CheckBox worker1 = findViewById(R.id.worker1);
        CheckBox worker2 = findViewById(R.id.worker2);
        CheckBox worker3 = findViewById(R.id.worker3);
        CheckBox worker4 = findViewById(R.id.worker4);
        CheckBox worker5 = findViewById(R.id.worker5);
        CheckBox worker6 = findViewById(R.id.worker6);

        int workerSalary;
        int salary = salary();

        if (i == 1) {
            if (worker1.isChecked())
                workerSalary = salary;
            else
                workerSalary = 0;
        } else if (i == 2) {
            if (worker2.isChecked())
                workerSalary = salary;
            else
                workerSalary = 0;
        } else if (i == 3) {
            if (worker3.isChecked())
                workerSalary = salary;
            else
                workerSalary = 0;
        } else if (i == 4) {
            if (worker4.isChecked())
                workerSalary = salary;
            else
                workerSalary = 0;
        } else if (i == 5) {
            if (worker5.isChecked())
                workerSalary = salary;
            else
                workerSalary = 0;
        } else if (i == 6) {
            if (worker6.isChecked())
                workerSalary = salary;
            else
                workerSalary = 0;
        } else workerSalary = 0;

        return workerSalary;
    }

    public void displaySummary(View view) {
        if (kasaForDay().length() > 0) {
            TextView summaryView = findViewById(R.id.summary);
            summaryView.setText(summary());
        } else
            showToast("Введіть значення каси!");
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
    }

    private String summary() {
        String summary = "Каса за день: " + kasaForDay() + " грн.";
        summary += "\n Зарплата:";
        summary += "\n Працівник1: " + workerSalary(1);
        summary += "\n Працівник2: " + workerSalary(2);
        summary += "\n Працівник3: " + workerSalary(3);
        summary += "\n Працівник4: " + workerSalary(4);
        summary += "\n Працівник5: " + workerSalary(5);
        summary += "\n Працівник6: " + workerSalary(6);
        return summary;
    }

    public void saveToDB(View view) {
        SQLiteDatabase tomYamDB1 = openOrCreateDatabase("table" + tableDate, MODE_PRIVATE, null);

        tomYamDB1.execSQL("CREATE TABLE IF NOT EXISTS table" + tableDate + " (kasaForDay String, salaryWorker1 int, " +
                "salaryWorker2 int, salaryWorker3 int, salaryWorker4 int, salaryWorker5 int, salaryWorker6 int)");
        ContentValues row1 = new ContentValues();
        row1.put("kasaForDay", kasaForDay());
        row1.put("salaryWorker1", workerSalary(1));
        row1.put("salaryWorker2", workerSalary(2));
        row1.put("salaryWorker3", workerSalary(3));
        row1.put("salaryWorker4", workerSalary(4));
        row1.put("salaryWorker5", workerSalary(5));
        row1.put("salaryWorker6", workerSalary(6));

        tomYamDB1.insert("table" + tableDate, null, row1);
        tomYamDB1.close();

        back();
    }

    public void back () {
        Intent intent = new Intent(KasaActivity.this, DayActivity.class);

        intent.putExtra("date", date);
        intent.putExtra("tableDate", tableDate);

        startActivity(intent);
    }
}

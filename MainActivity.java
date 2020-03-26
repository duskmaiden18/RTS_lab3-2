package com.example.lab32;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    private double speed, deadTime;
    private EditText result;
    private int range;
    private Spinner learnSpinner, deadlineSpinner, iterSpinner;

    private String[] learnRate = {"0.001", "0.01", "0.05", "0.1", "0.2", "0.3"};
    private String[] deadline = {"0.5", "1", "2", "5"};
    private String[] iterations = {"100", "200", "500", "1000",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.editText);

        learnSpinner = findViewById(R.id.learnRate);
        deadlineSpinner = findViewById(R.id.deadline);
        iterSpinner = findViewById(R.id.iter);

        spinner(learnSpinner, learnRate);
        spinner(deadlineSpinner, deadline);
        spinner(iterSpinner, iterations);
    }

    public void spinner(final Spinner spinner, String[] data) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.equals(learnSpinner)) {
                    speed = Double.parseDouble(learnSpinner.getSelectedItem().toString());
                } else if (spinner.equals(deadlineSpinner)) {
                    deadTime = Double.parseDouble(deadlineSpinner.getSelectedItem().toString());
                } else if (spinner.equals(iterSpinner)) {
                    range = Integer.parseInt(iterSpinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    public void onButtonClick(View v) {
        Perseptron perseptron = new Perseptron();
        double[][] start = {{0, 6}, {1, 5}, {3, 3}, {2, 4}};
        int[] end = {0, 0, 0, 1};
        LocalDateTime dateTime = LocalDateTime.now();
        int start = dateTime.toLocalTime().toSecondOfDay();
        int currentTime = dateTime.toLocalTime().toSecondOfDay();

        while (currentTime <= start + deadTime) {
            currentTime = dateTime.toLocalTime().toSecondOfDay();
            perseptron.study(start, end, 0.2, speed, range);
        }
        result.setText(perseptron.showResult());
    }
}

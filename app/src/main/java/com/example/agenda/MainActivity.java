package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mostraDialogoTimePicker(View v) {
        DialogFragment newFragment = new FragmentoTimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void mostraDialogoDatePicker(View v) {
        DialogFragment newFragment = new FragmentoDatePicker();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

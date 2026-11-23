package com.example.licznikzpaskiem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvCounter;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tvCounter = findViewById(R.id.tvCounter);

        setSupportActionBar(toolbar);
        tvCounter.setText(String.valueOf(number));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_plus) {
            number += 1;
            changeValue();
            return true;
        } else if (id == R.id.action_minus) {
            validateMinus();
            return true;
        } else if (id == R.id.action_zero) {
            confirmZero();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void validateMinus(){
        if(number == 0){
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            return;
        }
        number -= 1;
        changeValue();
    }

    private void changeValue(){
        tvCounter.setText(String.valueOf(number));
        setTitle(getString(R.string.title, number));
    }

    private void confirmZero(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_title)
                .setMessage(R.string.confirm_message)
                .setPositiveButton(R.string.yes, (dialog, which) -> {number = 0; changeValue();})
                .setNegativeButton(R.string.no, null)
                .show();
    }


}
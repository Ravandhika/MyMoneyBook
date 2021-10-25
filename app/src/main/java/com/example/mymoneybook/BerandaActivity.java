package com.example.mymoneybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BerandaActivity extends AppCompatActivity {

    ImageButton button_pemasukan, button_pengeluaran, button_detail, button_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button_pemasukan = findViewById(R.id.button_pemasukan);
        button_pengeluaran = findViewById(R.id.button_pengeluaran);

        button_pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, TambahPemasukanActivity.class);
                startActivity(intent);
            }
        });

        button_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, DetailCashFlowActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BerandaActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
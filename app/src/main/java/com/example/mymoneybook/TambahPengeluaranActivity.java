package com.example.mymoneybook;

import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class TambahPengeluaranActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    TextView tanggalText;
    ImageButton datePicker;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day;
    Calendar calendar;
    EditText nominal, keterangan;
    Button simpan, kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengeluaran);

        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        tanggalText = findViewById(R.id.tanggal);
        datePicker = findViewById(R.id.date_picker);

        nominal = findViewById(R.id.input_nominal);
        keterangan = findViewById(R.id.input_keterangan);

        simpan = findViewById(R.id.button_simpan);
        kembali = findViewById(R.id.button_kembali);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = newInstance(TambahPengeluaranActivity.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Tanggal Berapa Pemasukan Kamu");



                // Setting Max Date to next 2 years
                Calendar max_date_c = Calendar.getInstance();
                max_date_c.set(Calendar.YEAR, Year+2);
                datePickerDialog.setMaxDate(max_date_c);

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(TambahPengeluaranActivity.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqliteHelper databaseHelper = new SqliteHelper(TambahPengeluaranActivity.this);
                String nominalString = nominal.getText().toString();
                int nominalInt = Integer.parseInt(nominalString);
                databaseHelper.tambahPemasukan(tanggalText.getText().toString(), nominalInt, "pemasukan", keterangan.getText().toString());
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahPengeluaranActivity.this, BerandaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        tanggalText.setText(date);
    }
}
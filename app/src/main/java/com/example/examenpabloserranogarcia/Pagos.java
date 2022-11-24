package com.example.examenpabloserranogarcia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Pagos extends AppCompatActivity {

    private RadioButton tarjeta;
    private RadioButton paypal;
    private TextView totalEntradas;
    private TextView total;
    private TextView comision;
    private Intent intent;
    private String precioTotalIntent;
    private Double precioTotal;
    private Button pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        totalEntradas = findViewById(R.id.txt_NumTotalPagos);
        total = findViewById(R.id.txtNumTotalFinalPagos);
        comision = findViewById(R.id.txtNumComisionesPagos);
        paypal = findViewById(R.id.rbPaypal);
        tarjeta = findViewById(R.id.rbTarjeta);
        pagar = findViewById(R.id.btPagar);
        intent = getIntent();
        if(intent != null){
            precioTotalIntent = intent.getStringExtra(MainActivity.PRECIO);
            radioButtons();
        }
    }

    private void radioButtons() {
        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precioTotal = Double.valueOf(precioTotalIntent);
                comision.setText("0");
                totalEntradas.setText(String.valueOf(precioTotal));
                total.setText(precioTotalIntent);
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precioTotal = Double.valueOf(precioTotalIntent) + 2;
                comision.setText("2");
                totalEntradas.setText(precioTotalIntent);
                total.setText(String.valueOf(precioTotal));
            }
        });
    }

    public void pagar(View view) {
            Toast.makeText(this, "Pago realizado correctamente", Toast.LENGTH_LONG).show();
    }
}
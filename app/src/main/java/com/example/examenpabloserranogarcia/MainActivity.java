package com.example.examenpabloserranogarcia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String PRECIO = "comexampleexamenpabloserranogarcia";
    private Spinner spDia;
    private TextView entradasDisponibles;
    private EditText edtCantEntradas;
    private Button btCalcularPrecio;
    private Button btPago;
    private TextView txtPrecioEntradas;
    private TextView Descuento;
    private TextView Total;

    private String diaSeleccionado = null;
    private Double precioEntrada;
    private Double descuentoEntradas = 0.1;
    private Double precioTotal;
    private Double descuentoRealizado;
    private int disponibles = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entradasDisponibles = (TextView) findViewById(R.id.txt_CantidadEntradasDisponibles);
        edtCantEntradas = (EditText) findViewById(R.id.edt_CantidadEntradas);
        btCalcularPrecio = (Button) findViewById(R.id.bt_CalcularPrecio);
        btPago = (Button) findViewById(R.id.bt_Pago);
        txtPrecioEntradas = (TextView) findViewById(R.id.txt_numPrecioEntradas);
        Descuento = (TextView) findViewById(R.id.txt_numDescuento);
        Total = (TextView) findViewById(R.id.txt_numTotal);
        spDia = (Spinner) findViewById(R.id.sp_dia);

        entradasDisponibles.setText(String.valueOf(disponibles));
        if(spDia != null){
            String[] dias ={"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
            spDia.setOnItemSelectedListener(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilos_spinner, dias);
            spDia.setAdapter(adapter);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        diaSeleccionado = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        diaSeleccionado="Lunes";
    }

    public void calcularPrecios(View view) {
        String cantidad = String.valueOf(edtCantEntradas.getText());
        if(cantidad.isEmpty()){
            edtCantEntradas.setError("Debes indicar una cantidad de entradas");
            return;
        }
        else if(Integer.valueOf(cantidad) > disponibles || Integer.valueOf(cantidad) < 1){
            edtCantEntradas.setError("Solo hay " + disponibles + " entradas disponibles");
            return;
        }
        if (diaSeleccionado.equalsIgnoreCase("Sabado") || diaSeleccionado.equalsIgnoreCase("Domingo")){
            precioEntrada = 35 * Double.valueOf(String.valueOf(edtCantEntradas.getText()));
        }
        else if (!diaSeleccionado.equalsIgnoreCase("Sabado") || !diaSeleccionado.equalsIgnoreCase("Domingo")){
            precioEntrada = 30 * Double.valueOf(String.valueOf(edtCantEntradas.getText()));
        }
        if(Integer.valueOf(cantidad) > 3){
            descuentoRealizado = precioEntrada * descuentoEntradas;
        }
        else{
            descuentoRealizado = 0.0;
        }
        precioTotal = precioEntrada - descuentoRealizado;
        txtPrecioEntradas.setText(String.valueOf(precioEntrada));
        Descuento.setText(String.valueOf(descuentoRealizado));
        Total.setText(String.valueOf(precioTotal));

    }

    public void IrPagos(View view) {
        String cantidad = String.valueOf(edtCantEntradas.getText());
        if(cantidad.isEmpty()){
            edtCantEntradas.setError("Debes indicar una cantidad de entradas");
            return;
        }
        else if(Integer.valueOf(cantidad) > disponibles || Integer.valueOf(cantidad) < 1){
            edtCantEntradas.setError("Solo hay " + disponibles + " entradas disponibles");
            return;
        }
        Intent intent = new Intent(this, Pagos.class);
        intent.putExtra(PRECIO, String.valueOf(precioTotal));
        startActivity(intent);
    }
}
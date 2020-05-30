package com.example.calculadora_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Vista extends AppCompatActivity implements iCalculadora.iVista{

    private EditText num1;
    private EditText num2;
    private TextView result;
    private iCalculadora.iPresentador iPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=(EditText)findViewById(R.id.num1);
        num2=(EditText)findViewById(R.id.num2);
        result = (TextView) findViewById(R.id.res);
        iPresentador = new Presentador(this);
    }

    public void Sumar(View view){
        Log.e("MSG: ", String.valueOf(findViewById(R.id.suma)));
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Sumar");
    }

    @Override
    public void mostrarRespV(String resp) {
        result.setText(resp);
    }
}

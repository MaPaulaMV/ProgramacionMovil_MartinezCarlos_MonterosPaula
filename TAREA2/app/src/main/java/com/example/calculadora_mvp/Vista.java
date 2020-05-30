package com.example.calculadora_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Sumar");
    }

    public void Restar(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Restar");
    }

    public void Multiplicar(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Multiplicar");
    }

    public void Dividir(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Dividir");
    }

    @Override
    public void mostrarRespV(String resp) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        result.setText("RESULTADO: " + resp);
        inputMethodManager.hideSoftInputFromWindow(num1.getWindowToken(), 0);
    }

    @Override
    public void showErrorV(String error) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
        inputMethodManager.hideSoftInputFromWindow(num1.getWindowToken(), 0);
    }
}

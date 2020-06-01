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

/**
 * Permite instanciar y manejar todos los elementos de la
 * vista o interfaz de usuario.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Vista extends AppCompatActivity implements iCalculadora.iVista{

    private EditText num1;
    private EditText num2;
    private TextView result;
    private iCalculadora.iPresentador iPresentador;

    /**
     * Método que se encarga de inicializar los elemnetos de la vista.
     *
     * @param savedInstanceState Objeto Vista actual
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=(EditText)findViewById(R.id.num1);
        num2=(EditText)findViewById(R.id.num2);
        result = (TextView) findViewById(R.id.res);
        iPresentador = new Presentador(this);
    }

    /**
     * Método que envia los parámetros a la clase presentador para realizar
     * la suma.
     *
     * @param view Vista actual
     */
    public void Sumar(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Sumar");
    }

    /**
     * Método que envia los parámetros a la clase presentador para realizar
     * la resta.
     *
     * @param view Vista actual
     */
    public void Restar(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Restar");
    }

    /**
     * Método que envia los parámetros a la clase presentador para realizar
     * la multiplicación.
     *
     * @param view Vista actual
     */
    public void Multiplicar(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Multiplicar");
    }

    /**
     * Método que envia los parámetros a la clase presentador para realizar
     * la División.
     *
     * @param view Vista actual
     */
    public void Dividir(View view){
        iPresentador.calcularP(num1.getText().toString(),num2.getText().toString(),"Dividir");
    }

    /**
     * Método que muestra la respuesta retornada por el presentador
     * en un text View.
     *
     * @param resp Respuesta de la operación
     */
    @Override
    public void mostrarRespV(String resp) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        result.setText("RESULTADO: " + resp);
        inputMethodManager.hideSoftInputFromWindow(num1.getWindowToken(), 0);
    }

    /**
     * Método que muestra el mensaje de error retornado por
     * el presentador.
     *
     * @param error Mensaje de error
     */
    @Override
    public void showErrorV(String error) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
        inputMethodManager.hideSoftInputFromWindow(num1.getWindowToken(), 0);
    }
}

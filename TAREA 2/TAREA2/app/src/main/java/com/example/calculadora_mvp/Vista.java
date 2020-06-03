/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * Sistema: Calculadora_MVP
 * Creado 29/05/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package com.example.calculadora_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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

    private EditText numero1;
    private EditText numero2;
    private TextView resultado;
    private ImageButton suma;
    private ImageButton resta;
    private ImageButton multiplicacion;
    private ImageButton division;
    private ImageButton mplus;
    private ImageButton mrest;
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
        numero1 =(EditText)findViewById(R.id.num1);
        numero2 =(EditText)findViewById(R.id.num2);
        resultado = (TextView) findViewById(R.id.res);
        suma = (ImageButton) findViewById(R.id.suma);
        resta = (ImageButton) findViewById(R.id.resta);
        multiplicacion = (ImageButton) findViewById(R.id.multiplicacion);
        division = (ImageButton) findViewById(R.id.division);
        mplus = (ImageButton) findViewById(R.id.mPlus);
        mrest = (ImageButton) findViewById(R.id.mRest);
        iPresentador = new Presentador(this);

        /**
         * Setea el método onclick para que se envíen los parámetros al presentador para poder
         * realizar la operación Suma
         *
         */
        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.calcularP(numero1.getText().toString(), numero2.getText().toString(),"Sumar");
            }
        });

        /**
         * Setea el método onclick para que se envíen los parámetros al presentador para poder
         * realizar la operación Resta
         *
         */
        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.calcularP(numero1.getText().toString(), numero2.getText().toString(),"Restar");
            }
        });

        /**
         * Setea el método onclick para que se envíen los parámetros al presentador para poder
         * realizar la operación Multiplicación
         *
         */
        multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.calcularP(numero1.getText().toString(), numero2.getText().toString(),"Multiplicar");
            }
        });

        /**
         * Setea el método onclick para que se envíen los parámetros al presentador para poder
         * realizar la operación División
         *
         */
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.calcularP(numero1.getText().toString(), numero2.getText().toString(),"Dividir");
            }
        });

        /**
         * Setea el método onclick para que se envíen los parámetros al presentador para poder
         * realizar la operación MSuma
         *
         */
        mplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.calcularP(numero1.getText().toString(), numero2.getText().toString(),"mPLus");
            }
        });

        /**
         * Setea el método onclick para que se envíen los parámetros al presentador para poder
         * realizar la operación MResta
         *
         */
        mrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.calcularP(numero1.getText().toString(), numero2.getText().toString(),"mRest");
            }
        });
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
        resultado.setText("RESULTADO: " + resp);
        inputMethodManager.hideSoftInputFromWindow(numero1.getWindowToken(), 0);
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
        inputMethodManager.hideSoftInputFromWindow(numero1.getWindowToken(), 0);
    }
}

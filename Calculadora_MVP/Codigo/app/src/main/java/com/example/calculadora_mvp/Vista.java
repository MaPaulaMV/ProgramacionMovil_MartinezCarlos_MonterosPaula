/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * NRC: 6112
 *
 * Sistema: Calculadora_MVP
 * Creado 07/06/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package com.example.calculadora_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Clase que permite instanciar y manejar todos los elementos de la
 * vista o interfaz de usuario, implementa la interfaz iVista.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Vista extends AppCompatActivity implements iCalculadora.iVista{
    private iCalculadora.iPresentador iPresentador;
    private TextView input, resultado;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,
            btn6,btn7,btn8,btn9,btnpunto,
            btnsuma,btnresta,btnmult,btndiv,
            btnmplus,btnmrest,btnigual,btnpow,
            btnfact, btnmr, btnroot,btnlog,btnln,
            btnsin,btncos,btnpi,btnbin,btnoct,btnhex,btndec;
    private Button btnclear, btnmod,btnmm;
    private  ImageButton btndel;

    /**
     * Método que se encarga de inicializar los elemnetos de la vista.
     *
     * @param savedInstanceState Objeto Vista actual
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresentador = new Presentador(this);
        input = (TextView) findViewById(R.id.input);
        resultado = (TextView) findViewById(R.id.resultado);
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnsuma = (Button)findViewById(R.id.suma);
        btnresta = (Button)findViewById(R.id.resta);
        btnmult = (Button)findViewById(R.id.multiplicacion);
        btndiv = (Button)findViewById(R.id.division);
        btnclear = (Button) findViewById(R.id.borrar);
        btnpunto = (Button) findViewById(R.id.punto);
        btnigual = (Button) findViewById(R.id.igual);
        btnpow = (Button)findViewById(R.id.pow);
        btnfact = (Button)findViewById(R.id.fact);
        btnmplus = (Button)findViewById(R.id.mPlus);
        btnmrest = (Button)findViewById(R.id.mRest);
        btnmr = (Button)findViewById(R.id.mr);
        btnmod = (Button)findViewById(R.id.mod);
        btnmm = (Button)findViewById(R.id.mm);
        btnroot = (Button)findViewById(R.id.root);
        btnlog=(Button)findViewById(R.id.log);
        btnln=(Button)findViewById(R.id.ln);
        btnsin=(Button)findViewById(R.id.sin);
        btncos=(Button)findViewById(R.id.cos);
        btnpi=(Button)findViewById(R.id.pi);
        btndel=(ImageButton)findViewById(R.id.del);
        btnbin=(Button)findViewById(R.id.bin);
        btnoct=(Button)findViewById(R.id.oct);
        btnhex=(Button)findViewById(R.id.hex);
        btndec=(Button)findViewById(R.id.dec);

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn0.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn1.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn2.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn3.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn4.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn5.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn6.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn7.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn8.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que se envíen los parámetros y datos al presentador para poder
         * realizar las operaciones.
         */
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn9.getText().toString());
            }
        });

        /**
         * Setea el método onClick para borra o limpiar la pantalla llamando al método
         * del presentador.
         */
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickClearP();
            }
        });

        /**
         * Setea el método onClick para que el operador ingresado por teclado se envie como parámetro
         * al presentador.
         */
        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btnsuma.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que el operador ingresado por teclado se envie como parámetro
         * al presentador.
         */
        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btnresta.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que el operador ingresado por teclado se envie como parámetro
         * al presentador.
         */
        btnmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btnmult.getText().toString());
            }
        });

        /**
         * Setea el método onClick para que el operador ingresado por teclado se envie como parámetro
         * al presentador.
         */
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btndiv.getText().toString());
            }
        });

        /**
         * Setea el método onClick para enviar un punto (.) al presentador, indicando
         * el ingreso de un decimal.
         */
        btnpunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickDotP();
            }
        });

        /**
         * Setea el método onClick para mostrar el resultado de la operacióin y borrar la antreior cadena de
         * operaciones en pantalla.
         */
        btnigual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickEqualP();
            }
        });



        /**
         * Setea el método onClick para indicar que se está realizando la operación de
         * exponente.
         */
        btnpow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickPowP();
            }
        });

        /**
         * Setea el método onClick para indicar que se está realizando la operación de
         * factorial.
         */
        btnfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickFactorialP();
            }
        });

        /**
         * Setea el método onClick para indicar que se está realizando la operación de
         * M+.
         */
        btnmplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMplusP();
            }
        });

        /**
         * Setea el método onClick para indicar que se está realizando la operación de
         * M-.
         */
        btnmrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMrestP();
            }
        });

        /**
         * Setea el método onClick para indicar que se está realizando la operación de
         * MR.
         */
        btnmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMrP();

            }
        });

        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickModP();
            }
        });

        btnmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMMP();
            }
        });

        btnroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickRootP();
            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPresentador.onClickFuncionP(btnlog.getText().toString());
            }
        });

        btnln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPresentador.onClickFuncionP(btnln.getText().toString());
            }
        });

        btnsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPresentador.onClickFuncionP(btnsin.getText().toString());
            }
        });

        btncos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPresentador.onClickFuncionP(btncos.getText().toString());
            }
        });

        btnpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPresentador.onClickNumberP(String.valueOf(Math.PI));
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickDelP();
            }
        });

        btnbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickConvertP(btnbin.getText().toString());
            }
        });

        btnoct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickConvertP(btnoct.getText().toString());
            }
        });

        btnhex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickConvertP(btnhex.getText().toString());
            }
        });

        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickConvertP(btndec.getText().toString());
            }
        });

    }

    /**
     * Método que muestra en pantalla la cadena de operaciones realizada y
     * el resultado de la operación.
     *
     * @param scalculation Cadena de operaciones realizada.
     * @param sanswer Resultado de la cadea de operaciones.
     */
    @Override
    public void mostrarPantallaV(String scalculation, String sanswer) {
        input.setText(scalculation);
        resultado.setText(sanswer);
    }

    /**
     * Método que se encarga de mostrar en forma de mensaje o alerta
     * el valor que posee M.
     *
     * @param mr Resultado del valor que guarda en memoria M
     */
    @Override
    public void mostrarMr(String mr) {
        Toast.makeText(getApplicationContext(), "M: " + mr, Toast.LENGTH_LONG).show();
    }
}

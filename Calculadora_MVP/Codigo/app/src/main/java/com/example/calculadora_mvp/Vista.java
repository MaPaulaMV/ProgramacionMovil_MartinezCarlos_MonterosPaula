package com.example.calculadora_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Vista extends AppCompatActivity implements iCalculadora.iVista{
    private iCalculadora.iPresentador iPresentador;
    private TextView input, resultado;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,
            btn6,btn7,btn8,btn9,btnpunto,
            btnsuma,btnresta,btnmult,btndiv,
            btnmplus,btnmrest,btnigual,btnpow,btnfact, btnmr;
    private ImageButton btnclear;


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
        btnclear = (ImageButton)findViewById(R.id.borrar);
        btnpunto = (Button) findViewById(R.id.punto);
        btnigual = (Button) findViewById(R.id.igual);
        btnpow = (Button)findViewById(R.id.pow);
        btnfact = (Button)findViewById(R.id.fact);
        btnmplus = (Button)findViewById(R.id.mPlus);
        btnmrest = (Button)findViewById(R.id.mRest);
        btnmr = (Button)findViewById(R.id.mr);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn0.getText().toString());
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn1.getText().toString());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn2.getText().toString());
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn3.getText().toString());
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn4.getText().toString());
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn5.getText().toString());
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn6.getText().toString());
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn7.getText().toString());
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn8.getText().toString());
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickNumberP(btn9.getText().toString());
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickClearP();
            }
        });
        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btnsuma.getText().toString());
            }
        });
        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btnresta.getText().toString());
            }
        });
        btnmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btnmult.getText().toString());
            }
        });
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickOperatorP(btndiv.getText().toString());
            }
        });
        btnpunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickDotP();
            }
        });

        btnigual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickEqualP();
            }
        });

        btnpow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickPowP();
            }
        });

        btnfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickFactorialP();
            }
        });

        btnmplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMplusP();
            }
        });

        btnmrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMrestP();
            }
        });

        btnmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickMrP();

            }
        });

    }

    @Override
    public void mostrarPantallaV(String scalculation, String sanswer) {
        input.setText(scalculation);
        resultado.setText(sanswer);
    }

    @Override
    public void mostrarMr(String mr) {
        Toast.makeText(getApplicationContext(), "M: " + mr, Toast.LENGTH_LONG).show();
    }
}

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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
/**
 * Clase que permite instanciar y manejar todos los elementos de la
 * vista o interfaz de usuario para mostrar la gráfica de funciones trigonométricas,
 * implementa la interfaz iCalculadora.iVistaGraficadora.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class GraficadoraActivity extends AppCompatActivity implements iCalculadora.iVistaGraficadora{
    private iCalculadora.iPresentador iPresentador;
    LineGraphSeries <DataPoint> series;
    GraphView graphView;
    Button btnseno, btncoseno, btnclear;

    /**
     * Método que se encarga de inicializar los elemnetos de la vista.
     *
     * @param savedInstanceState Objeto Vista actual
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficadora);
        graphView = (GraphView) findViewById(R.id.graph);
        btnseno = (Button)findViewById(R.id.btnSen);
        btncoseno = (Button)findViewById(R.id.btnCos);
        btnclear = (Button)findViewById(R.id.btnClear);
        iPresentador = new Presentador(this);

        /**
         * Setea el método onClick para indicar que se va a graficar
         * la función Seno.
         */
        btnseno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickGraficarP(btnseno.getText().toString());

            }
        });

        /**
         * Setea el método onClick para indicar que se va a graficar
         * la función Coseno.
         */
        btncoseno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickGraficarP(btncoseno.getText().toString());
            }
        });

        /**
         * Setea el método onClick para indicar que se quiere borrar
         * los graficso de la pantalla.
         */
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

    }

    /**
     * Por medio de un conjunto de puntos entregados por el presentador
     * grafica la función en pantalla.
     *
     * @param puntos Serie de puntos
     */
    @Override
    public void graficar(LineGraphSeries<DataPoint> puntos) {
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(-1);
        graphView.getViewport().setMaxY(1);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(4);
        graphView.getViewport().setMaxX(10);

        // enable scaling and scrolling
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.addSeries(puntos);
    }

    /**
     * Limpiar o borrar las gráficas de la pantalla.
     */
    @Override
    public void limpiar() {
        graphView.removeAllSeries();
    }
}
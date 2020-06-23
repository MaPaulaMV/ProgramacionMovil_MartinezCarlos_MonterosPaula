package com.example.calculadora_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraficadoraActivity extends AppCompatActivity implements iCalculadora.iVistaGraficadora{
    private iCalculadora.iPresentador iPresentador;
    LineGraphSeries <DataPoint> series;
    GraphView graphView;
    Button btnseno, btncoseno, btnclear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficadora);
        graphView = (GraphView) findViewById(R.id.graph);
        btnseno = (Button)findViewById(R.id.btnSen);
        btncoseno = (Button)findViewById(R.id.btnCos);
        btnclear = (Button)findViewById(R.id.btnClear);
        iPresentador = new Presentador(this);

        btnseno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickGraficarP(btnseno.getText().toString());

            }
        });

        btncoseno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.onClickGraficarP(btncoseno.getText().toString());
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

    }

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

    @Override
    public void limpiar() {
        graphView.removeAllSeries();
    }
}
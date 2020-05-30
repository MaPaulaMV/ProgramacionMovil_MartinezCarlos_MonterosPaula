package com.example.calculadora_mvp;

public class Modelo implements iCalculadora.iModelo {
    private iCalculadora.iPresentador iPresentador;
    private double resultado;

    public Modelo(iCalculadora.iPresentador iPresentador){
        this.iPresentador = iPresentador;
    }
    @Override
    public void calcularM(String num1, String num2, String tipo) {

        if (tipo.equals("suma")){
            resultado = Double.valueOf(num1) + Double.valueOf(num2);
            iPresentador.mostrarRespP(String.valueOf(resultado));

        }else  if (tipo.equals("resta")){
            resultado = Double.valueOf(num1) - Double.valueOf(num2);
            iPresentador.mostrarRespP(String.valueOf(resultado));

        }
    }
}

package com.example.calculadora_mvp;

public class Presentador implements iCalculadora.iPresentador{
    private  iCalculadora.iVista iVista;
    private iCalculadora.iModelo iModelo;

    public Presentador(iCalculadora.iVista iVista){
        this.iVista = iVista;
        iModelo= new Modelo(this);
    }

    @Override
    public void mostrarRespP(String resp) {
        if (iVista != null){
            iVista.mostrarRespV(resp);
        }

    }

    @Override
    public void calcularP(String num1, String num2,String operacion) {
        if (iVista != null){
            iModelo.calcularM(num1,num2,operacion);
        }

    }

    @Override
    public void showErrorP(String error) {
        if (iVista != null){
            iVista.showErrorV(error);
        }
    }

}

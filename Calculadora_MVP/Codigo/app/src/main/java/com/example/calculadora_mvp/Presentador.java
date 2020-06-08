package com.example.calculadora_mvp;
import android.util.Log;

public class Presentador implements iCalculadora.iPresentador {
    private iCalculadora.iVista iVista;
    private iCalculadora.iModelo iModelo;

    public Presentador() {
    }

    public Presentador(iCalculadora.iVista iVista) {
        this.iVista = iVista;
    }

    public iCalculadora.iVista getiVista() {
        return iVista;
    }

    public void setiVista(iCalculadora.iVista iVista) {
        this.iVista = iVista;
    }

    public iCalculadora.iModelo getiModelo() {
        return iModelo;
    }

    public void setiModelo(iCalculadora.iModelo iModelo) {
        this.iModelo = iModelo;
    }

    @Override
    public void mostrarPantallaP(String scalculation, String sanswer) {
        try{
            if(iVista!=null){
                iVista.mostrarPantallaV(scalculation,sanswer);
            }
        }catch (Exception e){
            Log.e("ERROR mostrar respuesta", e.getMessage());
        }
    }

    @Override
    public void onClickNumberP(String valor) {
        try{
            if(iVista!=null){
                iModelo.onClickNumberM(valor);
            }
        }catch (Exception e){
            Log.e("ERROR ingreso de datos", e.getMessage());
        }
    }

    @Override
    public void onClickOperatorP(String operator) {
        try{
            if(iVista!=null){
                iModelo.onClickOperatorM(operator);
            }
        }catch (Exception e){
            Log.e("ERROR en operador", e.getMessage());
        }
    }

    @Override
    public void onClickClearP() {
        try{
            if(iVista!=null){
                iModelo.onClickClearM();
            }
        }catch (Exception e){
            Log.e("ERROR borrar pantalla", e.getMessage());
        }
    }

    @Override
    public void onClickDotP() {
        try{
            if(iVista!=null){
                iModelo.onClickDotM();
            }
        }catch (Exception e){
            Log.e("ERROR decimales", e.getMessage());
        }
    }

    @Override
    public void onClickEqualP() {
        try{
            if(iVista!=null){
                iModelo.onClickEqualM();
            }
        }catch (Exception e){
            Log.e("ERROR en el cálculo", e.getMessage());
        }
    }

    @Override
    public void onClickPowP() {
        try{
            if(iVista!=null){
                iModelo.onClickPowM();
            }
        }catch (Exception e){
            Log.e("ERROR potencia", e.getMessage());
        }
    }

    @Override
    public void onClickFactorialP() {
        try{
            if(iVista!=null){
                iModelo.onClickFactorialM();
            }
        }catch (Exception e){
            Log.e("ERROR factorial", e.getMessage());
        }
    }

    @Override
    public void onClickMplusP() {
        try{
            if(iVista!=null){
                iModelo.onClickMplusM();
            }
        }catch (Exception e){
            Log.e("ERROR M+", e.getMessage());
        }
    }

    @Override
    public void onClickMrestP() {
        try{
            if(iVista!=null){
                iModelo.onClickMrestM();
            }
        }catch (Exception e){
            Log.e("ERROR M-", e.getMessage());
        }

    }

    @Override
    public void onClickMrP() {
        try{
            if(iVista!=null){
                iModelo.onClickMrM();
            }
        }catch (Exception e){
            Log.e("ERROR MR", e.getMessage());
        }
    }
}

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
import android.util.Log;

/**
 * Clase que actúa como intermediario entre la Vista y
 * el Modelo de la aplicación, implementa la interfaz iCalculadora.iPresentador.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Presentador implements iCalculadora.iPresentador {
    private iCalculadora.iVista iVista;
    private iCalculadora.iModelo iModelo;

    /**
     * Constructor vacío de la clase Presentador.
     */
    public Presentador() {
    }

    /**
     * Constructor con parámetros de la clase Presentador.
     *
     * @param iVista Objeto que implementa la interfaz iCalculadora.iVista.
     */
    public Presentador(iCalculadora.iVista iVista) {
        this.iVista = iVista;
        this.iModelo=new Modelo(this);
    }

    /**
     * Método que retorna el valor del atributo iVista de la clase Presentador.
     *
     * @return Objeto que implementa la interfaz iCalculadora.iVista.
     */
    public iCalculadora.iVista getiVista() {
        return iVista;
    }

    /**
     * Método que setea el valor del atributo iVista de la clase Presentador.
     *
     * @param iVista Objeto que implemente la interfaz iCalculadora.iVista.
     */
    public void setiVista(iCalculadora.iVista iVista) {
        this.iVista = iVista;
    }

    /**
     * Método que retorna el valor del atributo iModelo de la clase Presentador.
     *
     * @return Objeto que implementa la interfaz iCalculadora.iModelo.
     */
    public iCalculadora.iModelo getiModelo() {
        return iModelo;
    }

    /**
     * Método que setea el valor del atributo iModelo de la clase Presentador.
     *
     * @param iModelo Objeto que implemente la interfaz iCalculadora.iModelo.
     */
    public void setiModelo(iCalculadora.iModelo iModelo) {
        this.iModelo = iModelo;
    }

    /**
     * Método que envia la cadena de operaciones realizadas y el resultado a la
     * vista de la aplicación para mostrar en pantalla.
     *
     * @param scalculation Cadena de operaciones.
     * @param sanswer Respuesta de la cadena de operaciones.
     */
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

    /**
     * Método que envia el resultado del valor de M a la clase Vista.
     *
     * @param mr Valor de M.
     */
    @Override
    public void mostrarMrP(String mr) {
        try{
            if(iVista!=null){
                iVista.mostrarMr(mr);
            }
        }catch (Exception e){
            Log.e("ERROR mostrar respuesta", e.getMessage());
        }
    }

    /**
     * Método que envía el dato o número ingresado a la clase Modelo.
     *
     * @param valor Número ingresado por el usuario.
     */
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

    /**
     * Método que envía al modelo un operador ingresado por el usuario en
     * la vista.
     *
     * @param operator Operador.
     */
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

    /**
     * Método que indica al modelo que limpie los datos de la calculadora.
     */
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

    /**
     * Método que indica al modelo que se ingresó un punto (.) o decimal.
     */
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

    /**
     * Método que indica al modelo que se desea mostrar en pantalla solo el resultado final
     * de la operación.
     */
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

    /**
     * Método que indica al modelo que realice la operación exponencial.
     */
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

    /**
     * Método que indica al modelo que realice la operación de factorial.
     */
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

    /**
     * Método que indica al modelo que realice la operación M+.
     */
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

    /**
     * Método que indica al modelo que realice la operación M-.
     */
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

    /**
     * Método que indica al modelo que realice la operación MR para
     * calcular el valor de M.
     */
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

    @Override
    public void onClickModP() {
        try{
            if(iVista!=null){
                iModelo.onClickModM();
            }
        }catch (Exception e){
            Log.e("ERROR MOD", e.getMessage());
        }
    }

    @Override
    public void onClickMMP() {
        try{
            if(iVista!=null){
                iModelo.onClickMM();
            }
        }catch (Exception e){
            Log.e("ERROR MP", e.getMessage());
        }
    }

    @Override
    public void onClickRootP() {
        try{
            if(iVista!=null){
                iModelo.onClickRootM();
            }
        }catch (Exception e){
            Log.e("ERROR ROOT", e.getMessage());
        }
    }

}

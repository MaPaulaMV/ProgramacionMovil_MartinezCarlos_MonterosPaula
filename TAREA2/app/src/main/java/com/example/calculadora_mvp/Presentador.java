package com.example.calculadora_mvp;

/**
 * Clase que actúa como intermediario entre la Vista y
 * el Modelo de la aplicación.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Presentador implements iCalculadora.iPresentador{
    private  iCalculadora.iVista iVista;
    private iCalculadora.iModelo iModelo;

    /**
     * Constructor. Constructor de la clase Presentador, implementa la
     * interfaz iPresentador de la clase iCalculadora.
     *
     * @param iVista Objeto que implementa la interfaz iVista
     */
    public Presentador(iCalculadora.iVista iVista){
        this.iVista = iVista;
        iModelo= new Modelo(this);
    }

    /**
     * Método que se encarga de enviar la respuesta retornada por el modelo
     * a la Vista.
     *
     * @param resp
     */
    @Override
    public void mostrarRespP(String resp) {
        if (iVista != null){
            iVista.mostrarRespV(resp);
        }

    }

    /**
     * Método que se encarga de enviar los parámetros al modelo para que
     * se realice la operación correspondiente.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @param operacion Tipo de operación a realizar
     */
    @Override
    public void calcularP(String num1, String num2,String operacion) {
        if (iVista != null){
            iModelo.calcularM(num1,num2,operacion);
        }

    }

    /**
     * Método que se encarga de enviar el mensaje de error retornado por
     * el modelo a la vista.
     *
     * @param error Mensaje de error
     */
    @Override
    public void showErrorP(String error) {
        if (iVista != null){
            iVista.showErrorV(error);
        }
    }

}

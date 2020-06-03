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

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Clase que contiene toda la lógica del programa, la misma realiza todas las operaciones.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Modelo implements iCalculadora.iModelo {
    private double mPlus = 0;
    private double mRest=0;
    private iCalculadora.iPresentador iPresentador;
    private double resultado;

    /**
     * Constructor vacío.
     */
    public Modelo (){}

    /**
     * Constructor. Constructor de la clase Modelo, implementa la interfaz iModelo
     * de la clase iCalculadora.
     *
     * @param iPresentador Objeto que implementa la interfaz iPresentador
     */
    public Modelo(iCalculadora.iPresentador iPresentador){
        this.iPresentador = iPresentador;
    }

    /**
     * Método que se encarga de realizar la operación seleccionada por el usuario,
     * calcula el resultado correspondiente entre los dos número.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @param tipo Tipo de operación a realizar
     * @return Resultado de la operación entre los dos números
     */
    @Override
    public String calcularM(String num1, String num2, String tipo) {
        String resu = "mal";
        try {
            if(!num1.equals("") && !num2.equals("")&& !tipo.equals("mPLus")&& !tipo.equals("mRest")){
                switch (tipo){
                    case "Sumar":
                        resultado = Double.valueOf(num1) + Double.valueOf(num2);
                        resu = String.valueOf(resultado);
                        iPresentador.mostrarRespP(String.valueOf(resultado));
                        break;

                    case "Restar":
                        resultado = Double.valueOf(num1) - Double.valueOf(num2);
                        resu = String.valueOf(resultado);
                        iPresentador.mostrarRespP(String.valueOf(resultado));
                        break;

                    case "Multiplicar":
                        resultado = Double.valueOf(num1) * Double.valueOf(num2);
                        resu = String.valueOf(resultado);
                        iPresentador.mostrarRespP(String.valueOf(resultado));
                        break;

                    case "Dividir":
                        if((Double.valueOf(num1)==0) && (Double.valueOf(num2)==0)){
                            iPresentador.showErrorP("Resultado Indefinido!");
                            break;
                        }else if(Double.valueOf(num2)==0){
                            iPresentador.showErrorP("No se puede dividir para 0!");
                            break;
                        }else {
                            resultado = Double.valueOf(num1) / Double.valueOf(num2);
                            resu = String.valueOf(resultado);
                            iPresentador.mostrarRespP(String.valueOf(resultado));
                            break;
                        }

                    default:
                        iPresentador.mostrarRespP("Operación incorrecta");
                        break;
                }

            }else if (((tipo.equals("mPLus"))|| (tipo.equals("mRest"))) && (!num1.equals("") || !num2.equals(""))){

                switch(tipo){
                    case "mPLus":
                        if(num1.equals("") ){
                            num1 = "0";
                            mPlus += Double.valueOf(num2);

                        }else if(num2.equals("") ){
                            num2 = "0";
                            mPlus += Double.valueOf(num1);
                        }
                        else {
                            mPlus += resultado;
                        }
                        resu = String.valueOf(mPlus);
                        Log.e("MPLUS", String.valueOf(mPlus));
                        //resu = String.valueOf(mplus);

                        iPresentador.mostrarRespP(String.valueOf(mPlus));
                        break;
                    case "mRest":
                        if(num1.equals("")){
                            num1 = "0";
                            mRest -= Double.valueOf(num2);

                        }else if(num2.equals("") ){

                            num2 = "0";
                            mRest -= Double.valueOf(num1);
                        }
                        else {

                            mRest -= resultado;
                        }

                        resu = String.valueOf(mRest);
                        Log.e("MREST", String.valueOf(mRest));

                        iPresentador.mostrarRespP(String.valueOf(mRest));
                        break;
                }
            }
            else{
                iPresentador.showErrorP("Es necesario ingresar los dos números!");

            }

        }
        catch (Exception e){

        }
        return resu;
    }
}

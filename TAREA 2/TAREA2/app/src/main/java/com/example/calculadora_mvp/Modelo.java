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
    private double mplus = 0;
    private double mrest=0;
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
                            mplus += Double.valueOf(num2);

                        }else if(num2.equals("") ){
                            num2 = "0";
                            mplus += Double.valueOf(num1);
                        }
                        else {
                            mplus += resultado;
                        }
                        resu = String.valueOf(mplus);
                        Log.e("MPLUS", String.valueOf(mplus));
                        //resu = String.valueOf(mplus);

                        iPresentador.mostrarRespP(String.valueOf(mplus));
                        break;
                    case "mRest":


                        if(num1.equals("")){

                            num1 = "0";
                            mrest -= Double.valueOf(num2);

                        }else if(num2.equals("") ){

                            num2 = "0";
                            mrest -= Double.valueOf(num1);
                        }
                        else {

                            mrest -= resultado;
                        }

                        resu = String.valueOf(mrest);
                        Log.e("MREST", String.valueOf(mrest));

                        iPresentador.mostrarRespP(String.valueOf(mrest));
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

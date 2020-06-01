package com.example.calculadora_mvp;

import android.util.Log;

/**
 * Clase que contiene toda la lógica del programa, la misma realiza todas las operaciones.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Modelo implements iCalculadora.iModelo {
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
            if(!num1.equals("") && !num2.equals("")){
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

            }else{
                iPresentador.showErrorP("Es necesario ingresar los dos números!");

            }

        }
        catch (Exception e){

        }
        return resu;
    }
}

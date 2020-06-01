package com.example.calculadora_mvp;

/**
 * Clase que contiene toda la lógica del programa, la misma realiza
 * todas las operaciones.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Modelo implements iCalculadora.iModelo {
    private iCalculadora.iPresentador iPresentador;
    private double resultado;

    /**
     * Constructor. Constructor de la clase Modelo, implementa la
     * interfaz iModelo de la clase iCalculadora.
     *
     * @param iPresentador  Objeto que implementa la interfaz iPresentador
     */
    public Modelo(iCalculadora.iPresentador iPresentador){
        this.iPresentador = iPresentador;
    }

    /**
     * Método que se encarga de realizar la operación seleccionada por el usuario, calcula el
     * resultado correspondiente entre los dos número.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @param tipo Tipo de operación a realizar
     */
    @Override
    public void calcularM(String num1, String num2, String tipo) {
        if(!num1.equals("") && !num2.equals("")){
            switch (tipo){
                case "Sumar":
                    resultado = Double.valueOf(num1) + Double.valueOf(num2);
                    iPresentador.mostrarRespP(String.valueOf(resultado));
                    break;

                case "Restar":
                    resultado = Double.valueOf(num1) - Double.valueOf(num2);
                    iPresentador.mostrarRespP(String.valueOf(resultado));
                    break;

                case "Multiplicar":
                    resultado = Double.valueOf(num1) * Double.valueOf(num2);
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
}

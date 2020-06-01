package com.example.calculadora_mvp;

import android.util.Log;

public class Modelo implements iCalculadora.iModelo {
    private iCalculadora.iPresentador iPresentador;
    private double resultado;

    public Modelo (){}
    public Modelo(iCalculadora.iPresentador iPresentador){
        this.iPresentador = iPresentador;
    }

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

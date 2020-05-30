package com.example.calculadora_mvp;

public class Modelo implements iCalculadora.iModelo {
    private iCalculadora.iPresentador iPresentador;
    private double resultado;

    public Modelo(iCalculadora.iPresentador iPresentador){
        this.iPresentador = iPresentador;
    }
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

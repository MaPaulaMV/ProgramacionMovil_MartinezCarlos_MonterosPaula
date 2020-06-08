package com.example.calculadora_mvp;

public class Operacion {

    public  Numero sumar(Numero num1, Numero num2){
            return new Numero(num1.getValor()+num2.getValor());
    }

    public  Numero restar(Numero num1, Numero num2){
        return new Numero(num1.getValor()-num2.getValor());
    }

    public  Numero factorial(Numero num1, Numero num2){
        Numero resultado = num1;
        for (int i = 1; i < num1.getValor(); i++) {
            resultado.setValor(resultado.getValor()*i);
        }
        return resultado;
    }
}

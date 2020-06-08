package com.example.calculadora_mvp;

import android.util.Log;

public class Operacion {

    public  Numero sumar(Numero num1, Numero num2){
            return new Numero(num1.getValor()+num2.getValor());
    }

    public  Numero restar(Numero num1, Numero num2){
        return new Numero(num1.getValor()-num2.getValor());
    }

    public Numero multiplicacion(Numero num1, Numero num2){
        return new Numero(num1.getValor()*num2.getValor());
    }

    public Numero division(Numero num1, Numero num2){
        return new Numero(num1.getValor()/num2.getValor());
    }

    public Numero exponencial(Numero num1,Numero num2){
        double base=num1.getValor();
        double exponente=num2.getValor();
        return new Numero(Math.pow(base,exponente));
    }

    public  Numero factorial(Numero num1) {
        double resultado =num1.getValor();
        for (int i = 1; i < num1.getValor(); i++) {
            resultado*=i;
        }
        return new Numero(resultado);
    }
}

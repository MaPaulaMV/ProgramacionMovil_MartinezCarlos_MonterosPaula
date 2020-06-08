package com.example.calculadora_mvp;

public class Operacion {

    public Numero multiplicacion(Numero num1, Numero num2){
        return new Numero(num1.getValor()*num2.getValor());
    }

    public Numero division(Numero num1, Numero num2){
        try{
            return new Numero(num1.getValor()/num2.getValor());
        }catch (Exception e){
            return null;
        }
    }

    public Numero exponencial(Numero num1,Numero num2){
        double base=num1.getValor();
        double exponente=num2.getValor();
        return new Numero(Math.pow(base,exponente));
    }
}

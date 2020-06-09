/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * NRC: 6112
 *
 * Sistema: Calculadora_MVP
 * Creado 07/06/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package com.example.calculadora_mvp;

import android.util.Log;

/**
 * Clase contiene todas las operaciones matemáticas de la aplicación.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Operacion {

    /**
     * Método que realiza la operación Suma.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @return Resultado de la suma
     */
    public  Numero sumar(Numero num1, Numero num2){
        return new Numero(num1.getValor()+num2.getValor());

    }

    /**
     * Método que realiza la operación Resta.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @return Resultado de la resta
     */
    public  Numero restar(Numero num1, Numero num2){
        return new Numero(num1.getValor()-num2.getValor());
    }

    /**
     * Método que realiza la operación Multiplicación.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @return Resultado de la multiplicación
     */
    public Numero multiplicacion(Numero num1, Numero num2){
        return new Numero(num1.getValor()*num2.getValor());
    }

    /**
     * Método que realiza la operación División.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @return Resultado de la división
     */
    public Numero division(Numero num1, Numero num2){
        return new Numero(num1.getValor()/num2.getValor());
    }

    /**
     * Método que realiza la operación exponente.
     *
     * @param num1 Primer número
     * @param num2 Segundo número
     * @return Resultado del exponente.
     */
    public Numero exponencial(Numero num1,Numero num2){
        double base=num1.getValor();
        double exponente=num2.getValor();
        return new Numero(Math.pow(base,exponente));
    }

    /**
     * Método que realiza el factorial de un número.
     *
     * @param num1 Número
     * @return Resultado del factorial del número.
     */
    public  Numero factorial(Numero num1) {
        double resultado =num1.getValor();

        for (int i = 1; i < num1.getValor(); i++) {
            resultado*=i;
        }

        return new Numero(resultado);
    }
}

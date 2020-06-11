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
        return new Numero(Math.pow(num1.getValor(),num2.getValor()));
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

    /**
     * Método que realiza la operación MOD, obteniendo el residuo
     * de la división entre 2 números
     *
     * @param numero1 Primer número
     * @param numero2 Segundo Número
     * @return Residuo de la división
     */
    public  Numero modulo (Numero numero1, Numero numero2){

        Double res = 0.0;
        res = numero1.getValor()%numero2.getValor();
        if (numero1.getValor()<0 && numero2.getValor()<0){
            return new Numero(res);
        }
        else {
            if (numero1.getValor()<0){
                return res<0 ? new Numero(res+numero2.getValor()): new Numero(res);
            }
            else if (numero2.getValor()<0){
                return res>0 ? new Numero(res+numero2.getValor()): new Numero(res);
            }
            else {
                return new Numero(res);
            }
        }
    }

    public Numero logNatural(Numero num1){
        double comp = 0.0;
        double denominador = 1.0;
        double x1 = (num1.getValor() - 1) / (num1.getValor() + 1);
        double x2 = x1 * x1;
        double fraccion = x1;
        double sum = fraccion;

        while (sum != comp) {
            comp = sum;
            denominador += 2.0;
            fraccion *= x2;
            sum += fraccion / denominador;
        }
        return new Numero(2.0*sum);
    }

    public Numero logBase10(Numero num1){
        double valor=0;
        int precision=10, cont=0,i;
        while (num1.getValor() !=1.0 && precision>=0){
            for(i=0;num1.getValor()>=10.0;i++){
                num1.setValor(num1.getValor()/10);
            }
            num1.setValor(Math.pow(num1.getValor(),10));
            valor=10*(valor+i);
            precision--;
            cont++;
        }
        return new Numero(valor/Math.pow(10,cont));
    }

    public Numero raiz(Numero numero){

        double n = 0, nn = 0, r, x = numero.getValor();
        double min = Double.MAX_VALUE;
        double aux;


        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            r = i * i;
            aux = Math.abs(r - x);
            if (aux < min) {//para ver si se pasa del nummero desdeado
                n = i;
                nn = r;
                min = aux;
            } else {
                break;
            }
        }

        double m_babilonico = (x + nn) / (2 * n);
        return new Numero(m_babilonico);
    }



}

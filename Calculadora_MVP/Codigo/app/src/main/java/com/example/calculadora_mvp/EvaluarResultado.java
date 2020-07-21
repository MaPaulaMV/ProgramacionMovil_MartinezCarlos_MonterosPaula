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
import java.util.Stack;

/**
 * Clase que se encarga de evaluar y calcular toda la expresión
 * postfija armada.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class EvaluarResultado {

    /**
     * Método que se encarga de calcular el resultado de toda la expresión postfija.
     *
     * @param postfijo Expresión postfija
     * @return Resultado de toda la operación
     */
    public static String Postfijo2resulTxt(String postfijo){
        String expr = postfijo;
        String[] post = expr.split(" ");


        Stack< String > E = new Stack < String > ();
        Stack < String > P = new Stack < String > ();


        for (int i=post.length-1; i>=0;i--) {
            E.push(post[i]);
        }


        String operadores = "+-*/^√!%lncs";

        while (!E.isEmpty()) {
            if (operadores.contains("" + E.peek())) {
                if("√".contains(E.peek()) || "!".contains(E.peek())||"l".contains(E.peek())||"n".contains(E.peek())||"s".contains(E.peek())||"c".contains(E.peek())){
                    P.push(evaluar(E.pop(), P.pop(), "0").getValor() + "");
                }
                else {
                    P.push(evaluar(E.pop(), P.pop(), P.pop()).getValor() + "");
                }

            }else {
                P.push(E.pop());
            }
        }

        String resultado = P.peek();
        return resultado;
    }

    /**
     * Método que se encarga de realizar la operación correspondiente al signo entre los
     * dos números ingresados como parámetreros.
     *
     * @param op Signo de la operación
     * @param n2 Segundo número
     * @param n1 Primer número
     * @return Resultado de la operación
     */
    private static Numero evaluar(String op, String n2, String n1) {
        Numero num1 = new Numero(Double.parseDouble(n1));
        Numero num2 = new Numero(Double.parseDouble(n2));
        Numero r = new Numero(0.0);
        Operacion operacion = new Operacion();
        String res;

        switch (op){
            case "+":
                return operacion.sumar(num1,num2);

            case "-":
                return operacion.restar(num1,num2);

            case "*":
                return operacion.multiplicacion(num1,num2);

            case "/":
                return operacion.division(num1,num2);
            case "^":
                return operacion.exponencial(num1,num2);

            case "√":
                return operacion.raiz(num2);

            case "!":
                return operacion.factorial(num2);

            case "%":
                return operacion.modulo(num1,num2);

            case "l":
                return operacion.logBase10(num2);

            case "n":
                return operacion.logNatural(num2);

            case "s":
                return operacion.seno(num2);

            case "c":
                return operacion.serieTaylor(num2);

            default:
                return new Numero(0.0);

        }
    }

}

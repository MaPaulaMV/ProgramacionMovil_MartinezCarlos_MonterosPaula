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

import java.security.spec.ECField;
import java.util.Stack;

/**
 * Clase que se encarga de pasar la cadena de operaciones a una expresión
 * postfija.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class PrefijoInfijo {

    /**
     * Método que se encarga de transformar la cadena de operaciones a una
     * expresión postfija para que pueda calcularse el resultado.
     *
     * @param infijo Cadena de operaciones
     * @return
     */
    public static String Infijo2PosfijoTxt(String infijo) {
        String postfij = null;
        String expr = depurar(infijo);

        String[] arrayInfix = expr.split(" ");


        Stack<String> E = new Stack<String>();
        Stack<String> P = new Stack<String>();
        Stack<String> S = new Stack<String>();

        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }

        try {

            while (!E.isEmpty()) {

                switch (pref(E.peek())) {

                    case 1:
                        P.push(E.pop());
                        break;

                    case 3:
                        while (pref(P.peek()) >= pref(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;

                    case 4:
                        while (pref(P.peek()) >= pref(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;

                    case 2:
                        while (!P.peek().equals("(")) {
                            S.push(P.pop());
                        }
                        P.pop();
                        E.pop();
                        break;

                    case 5:
                        while (pref(P.peek()) >= pref(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;

                    case 6:
                        while (pref(P.peek()) >= pref(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;

                    default:
                        S.push(E.pop());
                }
            }
            String infij = expr.replace(" ", "");
            postfij = S.toString().replaceAll("[\\]\\[,]", "");

        } catch (Exception ex) {
            //Log.e("POLACA", "ERROR");
        }
        return postfij;
    }

    /**
     * Método que se encarga de limpiar o depurar la cadena de
     * operaciones, separa cada valor o caracter por medio de espacios.
     *
     * @param s Cadena de operaciones
     * @return Cadena depurada
     */
    private static String depurar(String s) {
        s = s.replaceAll("\\s+", "");

        s = s.replaceAll("log","l");

        s = s.replaceAll("ln","n");

        s = s.replaceAll("sin","s");

        s = s.replaceAll("cos","c");

        s = "(" + s + ")";
        String simbols = "+-*/^()√!%lnsc";
        String str = "";


        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                try {
                    if (simbols.contains(""+s.charAt(i-1)) && (s.charAt(i)+"")!="√" && s.charAt(i-1)!='!' && s.charAt(i)!='l' && s.charAt(i)!='n' && s.charAt(i)!='s' && s.charAt(i)!='c'){
                        str+=s.charAt(i);
                    }
                    else {
                        str += " " + s.charAt(i) + " ";
                    }
                }catch (StringIndexOutOfBoundsException e){
                    str += " " + s.charAt(i) + " ";
                }

            } else {
                str += s.charAt(i);
            }
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    /**
     * Método que asigna un valor de acuerdo a la preferencia de signo de la
     * operación.
     *
     * @param oper Signo de la operación
     * @return Valor de la preferencia de signo
     */
    private static int pref(String oper) {
        int prf = 0;

        if (oper.equals("!") || oper.equals("l") || oper.equals("n")|| oper.equals("s")|| oper.equals("c")) {
            prf = 6;
        }
        if (oper.equals("^") || oper.equals("√")|| oper.equals("%")) {
            prf = 5;
        }
        if (oper.equals("*") || oper.equals("/") ) {
            prf = 4;
        }
        if (oper.equals("+") || oper.equals("-")) {
            prf = 3;
        }
        if (oper.equals(")")) {
            prf = 2;
        }
        if (oper.equals("(")) {
            prf = 1;
        }
        return prf;
    }
}

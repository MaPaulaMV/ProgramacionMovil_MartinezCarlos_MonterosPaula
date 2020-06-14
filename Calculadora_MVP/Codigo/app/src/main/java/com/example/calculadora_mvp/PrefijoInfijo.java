package com.example.calculadora_mvp;


import android.util.Log;

import java.security.spec.ECField;
import java.util.Stack;

public class PrefijoInfijo {

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
                    if (simbols.contains(""+s.charAt(i-1)) && s.charAt(i)!='√'&& s.charAt(i-1)!='!' && s.charAt(i)!='l'&& s.charAt(i)!='n'&& s.charAt(i)!='s'&& s.charAt(i)!='c'){
                        str+=s.charAt(i);
                    }
                    else {
                        str += " " + s.charAt(i) + " ";
                    }
                }catch (StringIndexOutOfBoundsException e){
                    str += " " + s.charAt(i) + " ";
                }

            } else str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
    }

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

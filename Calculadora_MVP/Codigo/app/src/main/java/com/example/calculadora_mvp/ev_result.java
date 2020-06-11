package com.example.calculadora_mvp;


import java.util.Stack;

public class ev_result {
    public static String Postfijo2resulTxt(String postfijo){
        String expr = postfijo;
        String[] post = expr.split(" ");


        Stack< String > E = new Stack < String > ();
        Stack < String > P = new Stack < String > ();


        for (int i=post.length-1; i>=0;i--) {
            E.push(post[i]);
        }


        String operadores = "+-*/^√";
        while (!E.isEmpty()) {
            if (operadores.contains("" + E.peek())) {
                P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
            }else {
                P.push(E.pop());
            }
        }

        String resultado = P.peek();
        return resultado;
    }

    private static float evaluar(String op, String n2, String n1) {
        float num1 = Float.parseFloat(n1);
        float num2 = Float.parseFloat(n2);

        if (op.equals("+")){
            return (num1 + num2);
        }
        if (op.equals("-")){
            return (num1 - num2);
        }
        if (op.equals("*")){
            return (num1 * num2);
        }
        if (op.equals("/")){
            return (num1 / num2);
        }
        if (op.equals("^")){
            float pot;
            pot = (float) Math.pow(num1,num2);
            return (pot);
        }
        if (op.equals("√")){
            float pot;
            pot = (float) Math.sqrt(num1);/*****/
            return (pot);
        }
        return 0;
    }

}

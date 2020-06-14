package com.example.calculadora_mvp;


import java.util.Stack;

public class EvaluarResultado {
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
                    P.push(evaluar(E.pop(), P.pop(), "0") + "");
                }
                else {
                    P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
                }

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
            pot = (float) Math.sqrt(num2);
            return (pot);
        }
        if (op.equals("!")){
            Operacion operacion = new Operacion();
            Numero r = new Numero(0.0);
            String res;
            r.setValor(operacion.factorial(new Numero(Double.parseDouble(n2))).getValor());
            res=r.getValor().toString();
            return ( Float.parseFloat(res));
        }
        if (op.equals("%")){
            Operacion operacion = new Operacion();
            Numero r = new Numero(0.0);
            String res;
            r.setValor(operacion.modulo(new Numero(Double.parseDouble(n1)),new Numero(Double.parseDouble(n2)) ).getValor());
            res=r.getValor().toString();
            return ( Float.parseFloat(res));
        }
        if (op.equals("l")){
            Operacion operacion = new Operacion();
            Numero r = new Numero(0.0);
            String res;
            r.setValor(operacion.logBase10(new Numero(Double.parseDouble(n2))).getValor());
            res=r.getValor().toString();
            return ( Float.parseFloat(res));
        }
        if (op.equals("n")){
            Operacion operacion = new Operacion();
            Numero r = new Numero(0.0);
            String res;
            r.setValor(operacion.logNatural(new Numero(Double.parseDouble(n2))).getValor());
            res=r.getValor().toString();
            return ( Float.parseFloat(res));
        }
        if (op.equals("c")){
            Operacion operacion = new Operacion();
            Numero r = new Numero(0.0);
            String res;
            r.setValor(operacion.serieTaylor(new Numero(Double.parseDouble(n2))).getValor());
            res=r.getValor().toString();
            return ( Float.parseFloat(res));
        }
        if (op.equals("s")){
            Operacion operacion = new Operacion();
            Numero r = new Numero(0.0);
            String res;
            r.setValor(operacion.seno(new Numero(Double.parseDouble(n2))).getValor());
            res=r.getValor().toString();
            return ( Float.parseFloat(res));
        }
        return 0;
    }

}

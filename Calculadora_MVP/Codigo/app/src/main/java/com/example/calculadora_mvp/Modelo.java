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

import android.graphics.Path;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Clase que contiene toda la lógica del programa, la misma realiza todas las operaciones
 * e implementa la interfaz iCalculadora.iModelo.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Modelo implements iCalculadora.iModelo{
    private iCalculadora.iPresentador iPresentador;
    private Numero result, numberone,temp, numbertwo, temp2;
    private Boolean dot= false, num_allow = true, pow_present = false, factorial_present=false;
    private NumberFormat format;
    private DecimalFormatSymbols simb = new DecimalFormatSymbols();
    private String scalculation, sanswer, numero_uno, current_operator, numero_dos;
    private Operacion operacion;

    /**
     * Constructor vacío de la clase Modelo.
     */
    public Modelo() {
    }

    /**
     * Constructor con parámetros de la clase Modelo.
     *
     * @param iPresentador Objeto que implementa la interfaz iCalculadora.iPresentador
     */
    public Modelo(iCalculadora.iPresentador iPresentador) {
        this.iPresentador = iPresentador;
        simb.setDecimalSeparator('.');
        format = new DecimalFormat("#.##########",simb);
        scalculation="";
        numero_uno = "";
        numero_dos = "";
        current_operator="";
        sanswer="";
        result = new Numero(0.0);
        numberone=new Numero(0.0);
        numbertwo=new Numero(0.0);
        temp=new Numero(0.0);
        temp2=new Numero(0.0);
        operacion=new Operacion();
    }

    /**
     * Método que retorna el valor del atributo iPresentador de la clase Modelo.
     *
     * @return Objeto que implementa la interfaz iCalculadora.iPresentador.
     */
    public iCalculadora.iPresentador getiPresentador() {
        return iPresentador;
    }

    /**
     * Método que setea el valor del atributo iPresentador de la clase Modelo.
     *
     * @param iPresentador Objeto que implemente la interfaz iCalculadora.iPresentador.
     */
    public void setiPresentador(iCalculadora.iPresentador iPresentador) {
        this.iPresentador = iPresentador;
    }

    /**
     * Método que agrega el valor o número ingresado a la cadena de operaciones,
     * realiza el cálculo de acuerdo al operador guardado y envía el resultado
     * al presentador.
     *
     * @param valor valor de un número.
     */
    @Override
    public void onClickNumberM(String valor) {
        if(num_allow){
            scalculation += valor;
            numero_uno+=valor;
            numberone.setValor(Double.parseDouble(numero_uno));

            switch (current_operator){
                case "":
                    if(pow_present){
                        Log.e("POW", numberone.toString());
                        Log.e("POW", numbertwo.toString());
                        temp=operacion.sumar(result,operacion.exponencial(numbertwo,numberone));

                    }
                    else {
                        temp=operacion.sumar(result,numberone);
                    }
                    break;
                case "+":
                    if(pow_present){
                        temp=operacion.sumar(result,operacion.exponencial(numbertwo,numberone));
                    }
                    else {
                        temp=operacion.sumar(result,numberone);
                    }
                    break;
                case "-":
                    if(pow_present){
                        temp=operacion.restar(result,operacion.exponencial(numbertwo,numberone));
                        Log.e("POW", temp.toString());
                    }
                    else {
                        temp=operacion.restar(result,numberone);
                    }
                    break;
                case "*":
                    if(pow_present){
                        temp=operacion.multiplicacion(result,operacion.exponencial(numbertwo,numberone));
                    }
                    else {
                        temp=operacion.multiplicacion(result,numberone);
                    }
                    break;
                case "/":
                    try {
                        if(pow_present){
                            temp=operacion.division(result,operacion.exponencial(numbertwo,numberone));
                        }
                        else {
                            /*if (numberone.getValor()!=0 && result.getValor()!=0){
                                temp=operacion.division(result,numberone);
                            }
                            else if (result.getValor()!=0 && numberone.getValor()==0){
                                char c = getLastChar(scalculation,2);
                                if (c=='/') {
                                    scalculation = scalculation.substring(0, scalculation.length() - 3);
                                }
                            }*/
                            temp=operacion.division(result,numberone);
                            Log.e("DIV", temp.getValor().toString());
                        }

                    }
                    catch (Exception e){
                        //sanswer = e.getMessage();
                    }
                    break;
            }
            sanswer = format.format(temp.getValor());
            iPresentador.mostrarPantallaP(scalculation,sanswer);
        }
    }

    /**
     * Método que agrega el operador a la cadena de operaciones guardada.
     *
     * @param operator Operador
     */
    @Override
    public void onClickOperatorM(String operator) {
        num_allow=true;
        if (sanswer!="" || operator.equals("-")){//para ver si es que hay un numero para hacer la operacion
            if(current_operator!=""){

                char c = getLastChar(scalculation,2);

                if (c=='+' || c=='-' ||c=='*' ||c=='/'){
                    scalculation=scalculation.substring(0,scalculation.length()-3);
                }
            }
            scalculation=scalculation + " "+ operator + " ";
            numero_uno="";
            result=temp;
            current_operator=operator;
            numbertwo.setValor(0.0);
            numero_dos="";
            pow_present=false;
            factorial_present=false; //la validacion se hace en la funcion de factorial mismo
            iPresentador.mostrarPantallaP(scalculation,sanswer);
            dot=false;
        }
    }

    /**
     * Método que borra o limpia todas la variables de la calculadora, excepto la
     * variable M.
     */
    @Override
    public void onClickClearM() {
        num_allow=true;
        scalculation="";
        sanswer="";
        current_operator="";
        numero_uno="";
        numero_dos="";
        result.setValor(0.0);
        numberone.setValor(0.0);
        numbertwo.setValor(0.0);
        temp.setValor(0.0);
        pow_present=false;
        factorial_present=false;
        Log.e("ENTRA",current_operator + "...");
        iPresentador.mostrarPantallaP(scalculation,sanswer);
        dot=false;
    }

    /**
     * Método que agrega un punto (.) a la cadena de operaciones, indicando
     * que el número es un decimal.
     */
    @Override
    public void onClickDotM() {
        if(!dot){
            if(numero_uno.length()==0 && sanswer.equals("")){
                numero_uno="0.";
                scalculation="0.";
                sanswer="0.";
                dot=true;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
            }else if(numero_uno.length()==0 && sanswer!=""){
                numero_uno="0.";
                scalculation +="0.";
                dot=true;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
            }
            else {
                numero_uno += ".";
                scalculation+=".";
                dot=true;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
            }
        }

    }

    /**
     * Método que limpia la cadena de operaciones y la inicializa con el resultado anterior,
     * de tal manera que en pantalla se muestre solo el resultado como nuevo inicio
     * para la cadena de operaciones.
     */
    @Override
    public void onClickEqualM() {
        if(scalculation!=""){
            scalculation=sanswer;
            sanswer=" ";
            result.setValor(0.0);
            numero_uno=scalculation;
            numberone.setValor(Double.valueOf(numero_uno));
            numero_dos="";
            numbertwo.setValor(0.0);
            num_allow=false;
            pow_present=false;
            dot=true;
            current_operator="";
            factorial_present=false;
            iPresentador.mostrarPantallaP(scalculation,sanswer);
        }
    }

    /**
     * Método que añade el operador o símbolo de exponente a la cadena de operaciones
     * e identifica que valor es la base y exponente para realizar el cálculo.
     */
    @Override
    public void onClickPowM() {

        if(scalculation!="" && !pow_present ){

            if(getLastChar(scalculation,1)!=' '){
                scalculation+="^";
                numero_dos=numero_uno;
                numbertwo.setValor(Double.valueOf(numero_dos));
                //numbertwo.setValor(numberone.getValor());
                numero_uno="";
                pow_present=true;
                num_allow=true;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
                Log.e("PW", numero_dos);
            }
        }
    }

    /**
     * Método que añade el operador o símbolo de factorial, ejecuta esta operación sobre un número
     * determinado, y suma el valor retornado al resultado de la cadena de operaciones.
     */
    @Override
    public void onClickFactorialM() {
        if (!scalculation.equals("") && !factorial_present && !pow_present && !dot) {

            if (getLastChar(scalculation, 1) != ' ') {

                if (numberone.getValor().equals(0.0)) {
                    numberone.setValor(1.0);
                }
                else{
                    numberone.setValor(operacion.factorial(numberone).getValor());
                }

                numero_uno = format.format(numberone.getValor());
                switch (current_operator) {
                    case "":
                        result = numberone;
                        break;
                    case "+":
                        result.setValor(result.getValor()+numberone.getValor());
                        break;
                    case "-":
                        result.setValor(result.getValor()-numberone.getValor());
                        break;
                    case "*":
                        result.setValor(result.getValor()*numberone.getValor());
                        break;
                    case "/":
                        try {
                            result.setValor(result.getValor()/numberone.getValor());
                        } catch (Exception e) {
                            sanswer = e.getMessage();
                        }

                        break;
                }
                dot=true;
                sanswer = result.getValor().toString();
                temp = result;
                scalculation += "! ";
                factorial_present = true;
                num_allow = false;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
            }
        }

    }

    /**
     * Método que se encarga de sumar un valor a la variable M de
     * la calculadora.
     */
    @Override
    public void onClickMplusM() {
        if (scalculation.equals("")){
            temp2.setValor(0.0);
        }
        else {
            temp2 =operacion.sumar(temp2,numberone);
        }

    }

    /**
     * Método que se encarga de restar un valor a la variable M de
     * la calculadora.
     */
    @Override
    public void onClickMrestM() {
        if (scalculation.equals("")){
            temp2.setValor(0.0);
        }
        else {
            temp2 =operacion.restar(temp2,numberone);
        }
    }

    /**
     * Método que envía al presentador el valor de la variable M
     * de la calculadora.
     */
    @Override
    public void onClickMrM() {
        iPresentador.mostrarMrP(temp2.getValor().toString());
    }

    /**
     * Método que obtiene el caracter indicado de acuerdo a una posición en
     * específico
     *
     * @param string
     * @param iterator Posición
     * @return Caracter
     */
    @Override
    public char getLastChar(String string, int iterator) {
        char c = string.charAt(string.length()-iterator);
        return  c;
    }
}

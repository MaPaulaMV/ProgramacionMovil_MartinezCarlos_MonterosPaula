package com.example.calculadora_mvp;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Modelo implements iCalculadora.iModelo{
    private iCalculadora.iPresentador iPresentador;
    private Numero result, numberone,temp, numbertwo, temp2;
    private Boolean dot= false, num_allow = true, pow_present = false, factorial_present=false;
    private NumberFormat format;
    private DecimalFormatSymbols simb = new DecimalFormatSymbols();
    private String scalculation, sanswer, numero_uno, current_operator, numero_dos;
    private Operacion operacion;

    public Modelo() {
    }

    public Modelo(iCalculadora.iPresentador iPresentador) {
        this.iPresentador = iPresentador;
        simb.setDecimalSeparator('.');
        format = new DecimalFormat("#.##########",simb);
        scalculation="";
        numero_uno = "";
        numero_dos = "";
        current_operator="";
        sanswer="";
        result.setValor(0.0);
        numberone.setValor(0.0);
        numbertwo.setValor(0.0);
        temp.setValor(0.0);
        temp2.setValor(0.0);
    }

    public iCalculadora.iPresentador getiPresentador() {
        return iPresentador;
    }

    public void setiPresentador(iCalculadora.iPresentador iPresentador) {
        this.iPresentador = iPresentador;
    }

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

                        }
                        temp=operacion.division(result,numberone);
                    }
                    catch (Exception e){
                        sanswer = e.getMessage();
                    }
                    break;
            }
            sanswer = format.format(temp);
            iPresentador.mostrarPantallaP(scalculation,sanswer);
        }
    }

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

    @Override
    public void onClickDotM() {

    }

    @Override
    public void onClickEqualM() {

    }

    @Override
    public void onClickPowM() {

    }

    @Override
    public void onClickFactorialM() {

    }

    @Override
    public void onClickMplusM() {

    }

    @Override
    public void onClickMrestM() {

    }

    @Override
    public void onClickMrM() {
        if (scalculation.equals("")){
            temp2.setValor(0.0);
        }
        else {
            temp2 =operacion.restar(temp2,numberone);
        }
        Log.e("MREST", temp2.toString());
    }

    @Override
    public char getLastChar(String string, int iterator) {
        char c = string.charAt(string.length()-iterator);
        return  c;
    }
}

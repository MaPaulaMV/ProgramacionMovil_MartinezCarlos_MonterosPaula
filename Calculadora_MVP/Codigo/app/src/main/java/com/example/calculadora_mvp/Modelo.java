package com.example.calculadora_mvp;

import android.graphics.Path;
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
        result = new Numero(0.0);
        numberone=new Numero(0.0);
        numbertwo=new Numero(0.0);
        temp=new Numero(0.0);
        temp2=new Numero(0.0);
        operacion=new Operacion();
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
            sanswer = format.format(temp.getValor());
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
            factorial_present=false;
            iPresentador.mostrarPantallaP(scalculation,sanswer);
        }
    }

    @Override
    public void onClickPowM() {
        if(scalculation!="" && !pow_present ){
            if(getLastChar(scalculation,1)!=' '){
                scalculation+="^";
                numero_dos=numero_uno;
                numbertwo.setValor(numberone.getValor());
                numero_uno="";
                pow_present=true;
                num_allow=true;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
            }
        }
    }

    @Override
    public void onClickFactorialM() {
        if (!scalculation.equals("") && !factorial_present && !pow_present && !dot) {

            if (getLastChar(scalculation, 1) != ' ') {

                if (numberone.getValor().equals(0.0)) {
                    numberone.setValor(1.0);
                }
                else{
                    operacion.factorial(numberone);
                }

                numero_uno = format.format(numberone.getValor());
                Log.e("ENTRA", numero_uno);
                Log.e("ENTRA", numberone.toString());
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
                sanswer = result.toString();
                temp = result;
                scalculation += "! ";
                factorial_present = true;
                num_allow = false;
                iPresentador.mostrarPantallaP(scalculation,sanswer);
            }
        }

    }

    @Override
    public void onClickMplusM() {
        if (scalculation.equals("")){
            temp2.setValor(0.0);
        }
        else {
            temp2 =operacion.sumar(temp2,numberone);
        }

    }

    @Override
    public void onClickMrestM() {
        if (scalculation.equals("")){
            temp2.setValor(0.0);
        }
        else {
            temp2 =operacion.restar(temp2,numberone);
        }
    }

    @Override
    public void onClickMrM() {
        iPresentador.mostrarMrP(temp2.getValor().toString());
    }

    @Override
    public char getLastChar(String string, int iterator) {
        char c = string.charAt(string.length()-iterator);
        return  c;
    }
}

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

/**
 * Interfaz principal del programa, donde se declara todos los métodos
 * que se van a usar para que puedan ser implementados por las diferentes clases.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public interface iCalculadora {

    /**
     * Interfaz iVista que declara los métodos que van a ser utilizados por las clases
     * que implementen esta interfaz.
     */
    interface iVista{
        void mostrarPantallaV(String scalculation, String sanswer);
        void mostrarMr(String mr);
    }

    /**
     * Interfaz iPresentador que declara los métodos que van a ser utilizados por las clases
     * que implementen esta interfaz.
     */
    interface iPresentador{
        void mostrarPantallaP(String scalculation, String sanswer);
        void mostrarMrP(String mr);
        void onClickNumberP(String valor);
        void onClickOperatorP(String operator);
        void onClickClearP();
        void onClickDotP();
        void onClickEqualP();
        void onClickPowP();
        void onClickFactorialP();
        void onClickMplusP();
        void onClickMrestP();
        void onClickMrP();
        void onClickModP();
        void onClickMMP();
        void onClickRootP();
    }

    /**
     * Interfaz iModelo que declara los métodos que van a ser utilizados por las clases
     * que implementen esta interfaz.
     */
    interface iModelo{
        void onClickNumberM(String valor);
        void onClickOperatorM(String operator);
        void onClickClearM();
        void onClickDotM();
        void onClickEqualM();
        void onClickPowM();
        void onClickFactorialM();
        void onClickMplusM();
        void onClickMrestM();
        void onClickMrM();
        void onClickModM();
        void onClickMM();
        void onClickRootM();
        char getLastChar(String string, int iterator);
    }
}

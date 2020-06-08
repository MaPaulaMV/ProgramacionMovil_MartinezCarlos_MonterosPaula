package com.example.calculadora_mvp;

public interface iCalculadora {

    interface iVista{
        void mostrarPantallaV(String scalculation, String sanswer);
        void mostrarMr(String mr);
    }

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
    }

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
        char getLastChar(String string, int iterator);
    }
}

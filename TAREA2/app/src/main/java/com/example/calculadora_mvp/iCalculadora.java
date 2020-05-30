package com.example.calculadora_mvp;

public interface iCalculadora {
    interface iVista{
        void mostrarRespV(String resp);
        void showErrorV(String error);
    }

    interface iPresentador{
        void mostrarRespP(String resp);
        void calcularP(String num1, String num2, String tipo);
        void showErrorP(String error);

    }

    interface iModelo{
        void calcularM(String num1, String num2, String tipo);
    }
}

package com.example.calculadora_mvp;

/**
 * Interfaz principal del programa, donde se instancian todos los métodos
 * que se van a usar para que puedan ser heredados por las diferentes clases.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public interface iCalculadora {

    /**
     * Interfaz que instancia los métodos de: mostrar respuesta y mostrar error
     * que van a ser utilizados por las clases que implementen esta interfaz.
     */
    interface iVista{
        void mostrarRespV(String resp);
        void showErrorV(String error);
    }

    /**
     * Interfaz que instancia los métodos de: mostrar respuesta, mostrar error y calcular
     * que van a ser utilizados por las clases que implementen esta interfaz.
     */
    interface iPresentador{
        void mostrarRespP(String resp);
        String calcularP(String num1, String num2, String tipo);
        void showErrorP(String error);

    }

    /**
     * Interfaz que instancia el método de calcular, el mismo que va a ser utilizados
     * por las clases que implementen esta interfaz.
     */
    interface iModelo{
        String calcularM(String num1, String num2, String tipo);
    }
}

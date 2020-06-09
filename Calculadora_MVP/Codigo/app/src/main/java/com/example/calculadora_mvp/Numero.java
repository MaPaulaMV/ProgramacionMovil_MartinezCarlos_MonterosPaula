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
 * Clase que representa el valor de un Número.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Numero {
    private Double valor;

    /**
     * Constructor. Constructor con parámetros de la clase Numero
     * @param valor
     */
    public Numero(Double valor) {
        this.valor = valor;
    }

    /**
     * Método que obtiene el valor del atributo Valor de la clase Numero.
     *
     * @return Valor del objeto Numero.
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Método que setea el valor del atributo Valor de la clase Numero.
     *
     * @param valor
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Método que transforma en string los atributos del objeto Numero.
     *
     * @return Valor del objeto Numero.
     */
    @Override
    public String toString() {
        return "Numero{" +
                "valor=" + valor +
                '}';
    }
}

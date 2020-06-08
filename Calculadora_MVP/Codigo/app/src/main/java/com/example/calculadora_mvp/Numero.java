package com.example.calculadora_mvp;

public class Numero {
    private Double valor;

    public Numero(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Numero{" +
                "valor=" + valor +
                '}';
    }
}

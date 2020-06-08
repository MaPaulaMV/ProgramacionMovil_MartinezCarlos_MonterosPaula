package com.example.calculadora_mvp;

import android.graphics.Path;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculadoraTest {

    private Operacion operacion;
    private Numero numero;
    private Numero numero1;
    private Numero numero2;

    @Before
    public void setUp() throws Exception {
        operacion = new Operacion();
        numero = new Numero(0.0);
        numero1 = new Numero(0.0);
        numero2 = new Numero(0.0);
    }

    @Test
    public void testNotNull(){
        assertNotNull(operacion);
    }

    @Test
    public void testSuma() {
        numero.setValor(3.0);numero1.setValor(1.0);numero2.setValor(2.0);
        assertEquals(numero.getValor(),operacion.sumar(numero1,numero2).getValor());
        numero.setValor(-5.0);numero1.setValor(-10.0);numero2.setValor(5.0);
        assertEquals(numero.getValor(),operacion.sumar(numero1,numero2).getValor());
        numero.setValor(4.28);numero1.setValor(2.14);numero2.setValor(2.14);
        assertEquals(numero.getValor(),operacion.sumar(numero1,numero2).getValor());
        numero.setValor(666000.0);numero1.setValor(-123456.0);numero2.setValor(789456.0);
        assertEquals(numero.getValor(),operacion.sumar(numero1,numero2).getValor());
    }

    @Test
    public void testResta() {
        numero.setValor(-1.0);numero1.setValor(1.0);numero2.setValor(2.0);
        assertEquals(numero.getValor(),operacion.restar(numero1,numero2).getValor());
        numero.setValor(-15.0);numero1.setValor(-10.0);numero2.setValor(5.0);
        assertEquals(numero.getValor(),operacion.restar(numero1,numero2).getValor());
        numero.setValor(0.0);numero1.setValor(2.14);numero2.setValor(2.14);
        assertEquals(numero.getValor(),operacion.restar(numero1,numero2).getValor());
        numero.setValor(-912912.0);numero1.setValor(-123456.0);numero2.setValor(789456.0);
        assertEquals(numero.getValor(),operacion.restar(numero1,numero2).getValor());
    }
    @Test
    public void testMultiplicacion() {
        numero.setValor(2.0);numero1.setValor(1.0);numero2.setValor(2.0);
        assertEquals(numero.getValor(),operacion.multiplicacion(numero1,numero2).getValor());
        numero.setValor(-50.0);numero1.setValor(-10.0);numero2.setValor(5.0);
        assertEquals(numero.getValor(),operacion.multiplicacion(numero1,numero2).getValor());
        numero.setValor(4.5796);numero1.setValor(2.14);numero2.setValor(2.14);
        assertEquals(numero.getValor(),operacion.multiplicacion(numero1,numero2).getValor());
        numero.setValor(-9.7463079936E10);numero1.setValor(-123456.0);numero2.setValor(789456.0);
        assertEquals(numero.getValor(),operacion.multiplicacion(numero1,numero2).getValor());
    }
    @Test
    public void testDivision() {
        numero.setValor(0.5);numero1.setValor(1.0);numero2.setValor(2.0);
        assertEquals(numero.getValor(),operacion.division(numero1,numero2).getValor());
        numero.setValor(-2.0);numero1.setValor(-10.0);numero2.setValor(5.0);
        assertEquals(numero.getValor(),operacion.division(numero1,numero2).getValor());
        numero.setValor(1.0);numero1.setValor(2.14);numero2.setValor(2.14);
        assertEquals(numero.getValor(),operacion.division(numero1,numero2).getValor());
        numero.setValor(-0.1563811029367058);numero1.setValor(-123456.0);numero2.setValor(789456.0);
        assertEquals(numero.getValor(),operacion.division(numero1,numero2).getValor());
    }

    @Test
    public void textExponencial(){
        numero.setValor(27.0);numero1.setValor(3.0);numero2.setValor(3.0);
        assertEquals(numero.getValor(),operacion.exponencial(numero1,numero2).getValor());
        numero.setValor(9.0);numero1.setValor(-3.0);numero2.setValor(2.0);
        assertEquals(numero.getValor(),operacion.exponencial(numero1,numero2).getValor());
        numero.setValor(1.0);numero1.setValor(3.0);numero2.setValor(0.0);
        assertEquals(numero.getValor(),operacion.exponencial(numero1,numero2).getValor());
        numero.setValor(-27.0);numero1.setValor(-3.0);numero2.setValor(3.0);
        assertEquals(numero.getValor(),operacion.exponencial(numero1,numero2).getValor());
    }

    @Test
    public void textFactorial(){
        numero.setValor(6.0);numero1.setValor(3.0);
        assertEquals(numero.getValor(),operacion.factorial(numero1).getValor());
    }


}
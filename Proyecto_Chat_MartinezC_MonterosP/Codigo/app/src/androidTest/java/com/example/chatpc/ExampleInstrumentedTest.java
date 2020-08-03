/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * NRC: 6112
 *
 * Sistema: CHATP&C
 * Creado 02/08/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package com.example.chatpc;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Clase para las pruebas unitarias del programa desarrollado.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /**
     * Prueba unitaria para el contexto de la aplicación.
     */
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.chatpc", appContext.getPackageName());
    }

    /**
     * Prueba unitaria para la creación de usuarios.
     */
    @Test
    public void user(){
        Usuario usuario=new Usuario();
        assertNotNull(usuario);
    }

    /**
     * Prueba unitaria para la creación de mensajes.
     */
    @Test
    public void mensaje(){
        Mensaje usuario=new Mensaje();
        assertNotNull(usuario);
    }

    /**
     * Prueba unitaria para la conexión con la base.
     */
    @Test
    public void coneccion(){
        Modelo modelo=new Modelo();
        assertNotNull(modelo);
    }
}
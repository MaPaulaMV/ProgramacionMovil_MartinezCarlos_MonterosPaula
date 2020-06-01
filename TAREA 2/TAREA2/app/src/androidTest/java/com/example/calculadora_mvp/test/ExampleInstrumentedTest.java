package com.example.calculadora_mvp.test;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Prueba instrumentada, que se ejecutará en un dispositivo Android.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * Uso del contexto de la aplicación.
     */
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.calculadora_mvp", appContext.getPackageName());
    }

    /*@Test
    public void testSuma(){
        assertEquals(2.0,2.3);
    }*/
}

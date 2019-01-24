package android.salesianostriana.com.a11_calculadoratest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculadoraTest {

    Calculadora calculadora;

    @Before
    public void setUp() throws Exception {
        calculadora = new Calculadora();
    }

    @Test
    public void calculadoraNotNull() {
        assertNotNull(calculadora);
    }

    @Test
    public void suma() {
        assertEquals("-1", calculadora.suma("-3", "2"));
    }

    @Test
    public void resta() {
        assertEquals("0", calculadora.resta("1", "1"));
    }
}
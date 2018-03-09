package br.com.alura.alurafood.validator;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class ValidaCPFTest {

    private static ValidaCPF validaCPF;

    @BeforeClass
    public static void setup() {
        validaCPF = new ValidaCPF();
    }

    @Test
    public void deveValidarCpfValido() {
        String cpfValido = "63874550060";
        assertTrue(validaCPF.valida(cpfValido));
    }

    @Test
    public void deveInvalidarCpfInvalido() {
        String cpfInvalido = "11111111111";
        assertFalse(validaCPF.valida(cpfInvalido));
    }

}
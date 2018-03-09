package br.com.alura.alurafood.validator;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidaTelefoneTest {

    private static ValidaTelefone validaTelefone;

    @BeforeClass
    public static void setup() {
        validaTelefone = new ValidaTelefone();
    }

    @Test
    public void deveInvalidarTelefoneComMenosDigitos() {
        String telefoneComSeteDigitosEDDD = "111111111";
        assertTrue(validaTelefone.invalido(telefoneComSeteDigitosEDDD));
    }

    @Test
    public void deveInvalidarTelefoneComMaisDigitos() {
        String telefoneComDezNumerosEDDD = "111111111111";
        assertTrue(validaTelefone.invalido(telefoneComDezNumerosEDDD));
    }

    @Test
    public void deveValidaTelefoneComDigitosEsperados() {
        String telefoneComOitoDigitosEDDD = "1111111111";
        assertTrue(validaTelefone.valida(telefoneComOitoDigitosEDDD));
        String telefoneComNoveDigitosEDDD = "11111111111";
        assertTrue(validaTelefone.valida(telefoneComNoveDigitosEDDD));
    }

}
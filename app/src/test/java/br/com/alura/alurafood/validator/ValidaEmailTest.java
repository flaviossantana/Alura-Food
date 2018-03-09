package br.com.alura.alurafood.validator;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class ValidaEmailTest {

    private static ValidaEmail validaEmail;

    @BeforeClass
    public static void setup() {
        validaEmail = new ValidaEmail();
    }

    @Test
    public void deveValidarEmailsComLetras() {
        String emailApenasComLetras = "alex@gmail.com";
        assertTrue(validaEmail.valida(emailApenasComLetras));
        String emailApenasComLetrasEbr = "alex@gmail.com.br";
        assertTrue(validaEmail.valida(emailApenasComLetrasEbr));
    }

    @Test
    public void deveValidarEmailsComNumerosELetras() {
        String emailComLetrasENumeros = "alex2018@gmail.com";
        assertTrue(validaEmail.valida(emailComLetrasENumeros));
        String emailComLetrasENumerosEbr = "alex2018@gmail.com.br";
        assertTrue(validaEmail.valida(emailComLetrasENumerosEbr));
        String emailComNumeros = "2018@gmail.com";
        assertTrue(validaEmail.valida(emailComNumeros));
        String emailComNumerosEbr = "2018@gmail.com.br";
        assertTrue(validaEmail.valida(emailComLetrasENumerosEbr));
    }

    @Test
    public void deveValidarEmailsComCaracteresEspeciaisComuns() {
        String emailComLetrasECaracteres = "alex-felipe_victor.vieira@gmail.com";
        assertTrue(validaEmail.valida(emailComLetrasECaracteres));
        String emailComLetrasECaracteresEbr = "alex-felipe_victor.vieira@gmail.com.br";
        assertTrue(validaEmail.valida(emailComLetrasECaracteresEbr));
    }

    @Test
    public void deveValidarEmailsSemDominio() {
        String emailSemDominio = "alex@.com";
        assertFalse(validaEmail.valida(emailSemDominio));
    }

}
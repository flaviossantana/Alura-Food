package br.com.alura.alurafood.validator;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidaNomeTest {

    @Mock
    private EditText editText;
    private ValidaNome validaNome;

    @Before
    public void setup() {
        validaNome = new ValidaNome(editText);
    }

    @Test
    public void deveValidaNomesQueIniciamEmmaiusculo() {
        boolean nomeComSobrenome = validaNome.valida("Alex Felipe");
        assertTrue(nomeComSobrenome);
    }

    @Test
    public void deveInvalidarNomesQueIniciamEmMinusculo() {
        boolean nomeEmMinusculo = validaNome.valida("alex");
        assertFalse(nomeEmMinusculo);
        boolean nomeComSobrenomeMinusculo = validaNome.valida("alex felipe");
        assertFalse(nomeComSobrenomeMinusculo);
    }

    @Test
    public void deveValidarNomePreposicaoNoSobrenome(){
        boolean nomeComPreposicao = validaNome.valida("Maria da Silva");
        assertTrue(nomeComPreposicao);
        boolean nomeComPreposicaoNoPlural = validaNome.valida("Maria das Graças");
        assertTrue(nomeComPreposicaoNoPlural);
        boolean nomeComPreposicaoInvalida = validaNome.valida("Maria da");
        assertFalse(nomeComPreposicaoInvalida);
        boolean nomeComDuasPreposicoesJuntas = validaNome.valida("Maria da da");
        assertFalse(nomeComDuasPreposicoesJuntas);
    }

}
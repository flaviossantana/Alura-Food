package br.com.alura.alurafood.validator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidaSenhaTest {

    @Test
    public void deveValidaSenhaComOitoDigitos(){
        ValidaSenha validaSenha = new ValidaSenha.Builder()
                .build();
        String senha = "a3c[~].;";
        assertTrue(validaSenha.valida(senha));
    }

    @Test
    public void deveValidarSenhaComOitoLetras() {
        ValidaSenha validaSenha = new ValidaSenha.Builder()
                .comLetraMinuscula()
                .build();
        String senha = "alexfeli";
        assertTrue(validaSenha.valida(senha));
    }

}
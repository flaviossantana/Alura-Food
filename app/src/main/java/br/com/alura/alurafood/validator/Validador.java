package br.com.alura.alurafood.validator;

public interface Validador {
    boolean valida(String texto);
    String getErro();
}

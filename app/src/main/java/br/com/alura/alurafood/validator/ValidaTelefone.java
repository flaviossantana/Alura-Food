package br.com.alura.alurafood.validator;

public class ValidaTelefone implements ValidaEditText {

    public boolean invalido(String telefone) {
        return !valida(telefone);
    }

    @Override
    public boolean valida(String telefone) {
        return telefone.matches("[0-9]{2}[0-9]{8,9}");
    }
}

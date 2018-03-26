package br.com.alura.alurafood.validator;

public class ValidaNumero extends EditTextValidadorPadrao {

    @Override
    public boolean valida(String texto) {
        if (super.valida(texto)) {
            return validacaoPersonalizada(texto);
        }
        return false;
    }

    private boolean validacaoPersonalizada(String texto) {
        if (texto.length() < 5) {
            erro = "Precisa ter pelo menos 5 dígitos";
            return false;
        }
        return true;
    }

}

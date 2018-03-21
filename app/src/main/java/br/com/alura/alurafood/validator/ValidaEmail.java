package br.com.alura.alurafood.validator;

public class ValidaEmail implements Validador {

    private static final String PADRAO_EMAIL = ".+@.+\\..+";
    public static final String EMAIL_INVALIDO = "E-mail inválido";
    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private String erro;

    @Override
    public boolean valida(String email) {

        if (email.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }
        if (!email.matches(PADRAO_EMAIL)) {
            erro = EMAIL_INVALIDO;
            return false;
        }
        return true;
    }

    @Override
    public String getErro() {
        return erro;
    }
}

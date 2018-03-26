package br.com.alura.alurafood.validator;

public class ValidaCep implements Validador {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private String erro = "";

    @Override
    public boolean valida(String cep) {

        if (cep.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }

        if (cep.length() != 8) {
            erro = "CEP precisa de 8 dígitos";
            return false;
        }

        erro = "";
        return true;
    }

    @Override
    public String getErro() {
        return erro;
    }
}

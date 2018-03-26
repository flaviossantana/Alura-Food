package br.com.alura.alurafood.validator;

public class ValidaTelefone implements Validador {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    public static final String LIMITE_CARACTERES = "Precisa ter entre 10 a 11 dígitos";
    private String erro = "";

    @Override
    public boolean valida(String telefone) {

        if (telefone.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }

        int caracteres = telefone.length();
        if (caracteres < 10 || caracteres > 11) {
            erro = LIMITE_CARACTERES;
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

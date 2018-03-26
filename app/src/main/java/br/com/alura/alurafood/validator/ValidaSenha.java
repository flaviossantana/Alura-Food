package br.com.alura.alurafood.validator;

public class ValidaSenha implements Validador {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private String erro = "";

    @Override
    public boolean valida(String senha) {
        if (senha.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
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

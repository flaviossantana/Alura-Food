package br.com.alura.alurafood.validator;

public class ValidaNome implements Validador {
    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private String erro;

    @Override
    public boolean valida(String nome) {
        if (nome.isEmpty()) {
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

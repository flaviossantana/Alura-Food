package br.com.alura.alurafood.validator;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class EditTextValidadorPadrao {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private List<EditText> campos = new ArrayList<>();
    protected String erro;

    public EditTextValidadorPadrao() {

    }

    public void add(EditText... campos) {
        for (EditText campo :
                campos) {
            this.campos.add(campo);
            aplicaValidacaoPadrao(campo);
        }
    }

    public void aplicaValidacaoPadrao(EditText campo) {
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String texto = campo.getText().toString();
                if (valida(texto)) {
                    campo.setError(null);
                } else {
                    campo.setError(getErro());
                }
            }
        });
    }

    public boolean valida(String texto) {

        if (texto.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }

        limpaErro();
        return true;
    }

    private void limpaErro() {
        erro = "";
    }


    public String getErro() {
        return erro;
    }

}

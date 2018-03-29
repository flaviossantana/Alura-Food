package br.com.alura.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidaEmail extends ValidadorPadrao {

    private static final String PADRAO_EMAIL = ".+@.+\\..+";
    private static final String EMAIL_INVALIDO = "E-mail inválido";
    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";

    public ValidaEmail(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    private ValidaEmail(EditText campo) {
        super(campo);
        setEmValidacao(adicionaValidacao());
    }

    @NonNull
    private EmValidacao adicionaValidacao() {
        return email -> {
            if (email.isEmpty()) {
                erro = CAMPO_OBRIGATORIO;
                return false;
            }
            if (!email.matches(PADRAO_EMAIL)) {
                erro = EMAIL_INVALIDO;
                return false;
            }
            return true;
        };
    }

}

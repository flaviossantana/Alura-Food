package br.com.alura.alurafood.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidadorPadrao implements Validador {

    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    public static final String VAZIO = "";
    final EditText campo;
    private EmValidacao emValidacao = texto -> true;
    private EstadoDeValidacao estadoDeValidacao = new EstadoDeValidacao() {
        @Override
        public void estaValido(String texto) {

        }

        @Override
        public void naoEstaValido(String texto) {

        }
    };
    String erro;
    TextInputLayout textInputLayout;

    public ValidadorPadrao(EditText campo) {
        this.campo = campo;
        this.campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                valida();
            }
        });
    }

    public ValidadorPadrao(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    void setEmValidacao(EmValidacao emValidacao) {
        this.emValidacao = emValidacao;
    }

    void setEstadoDeValidacao(EstadoDeValidacao estadoDeValidacao) {
        this.estadoDeValidacao = estadoDeValidacao;
    }

    @Override
    public boolean valida() {
        String texto = campo.getText().toString();
        if (valido()) {
            mostraErro();
            estadoDeValidacao.estaValido(texto);
            return true;
        }
        escondeErro();
        estadoDeValidacao.naoEstaValido(texto);
        return false;
    }

    private void escondeErro() {
        if (textInputLayout != null) {
            textInputLayout.setError(erro);
        } else {
            campo.setError(erro);
        }
    }

    private void mostraErro() {
        if (textInputLayout != null) {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        } else {
            campo.setError(null);
        }
    }

    private boolean valido() {
        String texto = campo.getText().toString();

        if (texto.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }

        if (!emValidacao.adicionaValidacao(texto)) {
            return false;
        }

        limpaErro();
        return true;
    }

    private void limpaErro() {
        erro = VAZIO;
    }

    interface EmValidacao {
        boolean adicionaValidacao(String texto);
    }

    interface EstadoDeValidacao {
        void estaValido(String texto);

        void naoEstaValido(String texto);
    }

}

package br.com.alura.alurafood.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.caelum.stella.type.Estado;

public class ValidadorPadrao implements Validador {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    protected EditText campo;
    private EmValidacao emValidacao = texto -> true;
    private EstadoDeValidacao estadoDeValidacao = new EstadoDeValidacao() {
        @Override
        public void estaValido(String texto) {

        }

        @Override
        public void naoEstaValido(String texto) {

        }
    };
    protected String erro;
    protected TextInputLayout textInputLayout;

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

    public void setEmValidacao(EmValidacao emValidacao) {
        this.emValidacao = emValidacao;
    }

    public void setEstadoDeValidacao(EstadoDeValidacao estadoDeValidacao) {
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

    protected boolean valido() {
        String texto = campo.getText().toString();

        if (texto.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }

        if (!emValidacao.valida(texto)) {
            return false;
        }

        limpaErro();
        return true;
    }

    private void limpaErro() {
        erro = "";
    }

    interface EmValidacao {
        boolean valida(String texto);
    }

    interface EstadoDeValidacao {
        void estaValido(String texto);

        void naoEstaValido(String texto);
    }

}

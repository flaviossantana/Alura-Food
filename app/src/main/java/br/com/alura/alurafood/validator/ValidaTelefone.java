package br.com.alura.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.alura.alurafood.formatter.Formatador;
import br.com.alura.alurafood.formatter.formataTelefone;

public class ValidaTelefone extends ValidadorPadrao {

    public static final String LIMITE_CARACTERES = "Precisa ter entre 10 a 11 dígitos";
    private Formatador formatador = new formataTelefone();

    public ValidaTelefone(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    public ValidaTelefone(EditText campo) {
        super(campo);
        campo.setOnFocusChangeListener(configuraEstadoDeFoco(campo));
        setEmValidacao(adicionaValidacao());
        setEstadoDeValidacao(new EstadoDeValidacao() {
            @Override
            public void estaValido(String telefone) {
                mostraMascara(telefone);
            }

            @Override
            public void naoEstaValido(String telefone) {
                removeMascara(telefone);
            }
        });
    }

    @NonNull
    private View.OnFocusChangeListener configuraEstadoDeFoco(EditText campo) {
        return (v, hasFocus) -> {
            String telefone = campo.getText().toString();
            if (hasFocus) {
                removeMascara(telefone);
            } else if (super.valida()) {
                mostraMascara(telefone);
            }
        };
    }

    @NonNull
    private EmValidacao adicionaValidacao() {
        return telefone -> {
            String telefoneSemMascara = formatador.semMascara(telefone);
            int caracteres = telefoneSemMascara.length();
            if (caracteres < 10 || caracteres > 11) {
                erro = LIMITE_CARACTERES;
                return false;
            }
            return true;
        };
    }

    private void removeMascara(String telefone) {
        String telefoneSemMascara = formatador.semMascara(telefone);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        campo.setText(telefoneSemMascara);
    }

    private void mostraMascara(String telefone) {
        String telefoneComMascara = formatador.comMascara(telefone);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        campo.setText(telefoneComMascara);
    }

}

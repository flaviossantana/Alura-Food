package br.com.alura.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.alura.alurafood.formatter.Formatador;
import br.com.alura.alurafood.formatter.FormataTelefone;

public class ValidaTelefone extends ValidadorPadrao {

    private static final int DIGITOS_MAXIMO = 11;
    private static final int DIGITOS_MINIMO = 10;
    private static final String LIMITE_CARACTERES = "Precisa ter entre " + DIGITOS_MINIMO +
            " a " + DIGITOS_MAXIMO + " dígitos";
    private static final int DIGITOS_SEM_MASCARA = 11;
    private static final int DIGITOS_COM_MASCARA = 15;
    private final Formatador formatador = new FormataTelefone();

    public ValidaTelefone(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    private ValidaTelefone(EditText campo) {
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
            if (naoEstaNoLimiteDeDigitos(caracteres)) {
                erro = LIMITE_CARACTERES;
                return false;
            }
            return true;
        };
    }

    private boolean naoEstaNoLimiteDeDigitos(int caracteres) {
        return caracteres < DIGITOS_MINIMO || caracteres > DIGITOS_MAXIMO;
    }

    private void removeMascara(String telefone) {
        String telefoneSemMascara = formatador.semMascara(telefone);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DIGITOS_SEM_MASCARA)});
        campo.setText(telefoneSemMascara);
    }

    private void mostraMascara(String telefone) {
        String telefoneComMascara = formatador.comMascara(telefone);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DIGITOS_COM_MASCARA)});
        campo.setText(telefoneComMascara);
    }

}

package br.com.alura.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import br.com.alura.alurafood.formatter.FormataCpf;
import br.com.alura.alurafood.formatter.Formatador;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class ValidaCpf extends ValidadorPadrao {

    private static final String LIMITE_CARACTERES = "CPF deve ter 11 dígitos";
    private static final String INVALIDO = "CPF inválido";
    private final Formatador formatador = new FormataCpf();

    public ValidaCpf(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    private ValidaCpf(EditText campo) {
        super(campo);
        campo.setOnFocusChangeListener(configuraEstadoDeFoco(campo));
        setEmValidacao(adicionaValidacao());
        setEstadoDeValidacao(configuraEstadoDeValidacao());
    }

    @NonNull
    private EstadoDeValidacao configuraEstadoDeValidacao() {
        return new EstadoDeValidacao() {
            @Override
            public void estaValido(String cpf) {
                String cpfSemMascara = formatador.semMascara(cpf);
                mostraMascara(cpfSemMascara);
            }

            @Override
            public void naoEstaValido(String cpf) {
                removeMascara(cpf);
            }
        };
    }

    @NonNull
    private View.OnFocusChangeListener configuraEstadoDeFoco(EditText campo) {
        return (v, hasFocus) -> {
            String cpf = campo.getText().toString();
            if (hasFocus) {
                removeMascara(cpf);
            } else if (super.valida()) {
                mostraMascara(cpf);
            }
        };
    }

    @NonNull
    private EmValidacao adicionaValidacao() {
        return cpf -> {
            String cpfSemMascara = formatador.semMascara(cpf);
            if (cpfSemMascara.length() != 11) {
                erro = LIMITE_CARACTERES;
                return false;
            }

            CPFValidator validador = new CPFValidator();
            List<ValidationMessage> erros = validador.invalidMessagesFor(cpfSemMascara);
            if (!erros.isEmpty()) {
                erro = INVALIDO;
                return false;
            }

            return true;
        };
    }

    private void removeMascara(String cpf) {
        String cpfSemMascara = formatador.semMascara(cpf);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        campo.setText(cpfSemMascara);
    }

    private void mostraMascara(String cpf) {
        String cpfComMascara = formatador.comMascara(cpf);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        campo.setText(cpfComMascara);
    }

}

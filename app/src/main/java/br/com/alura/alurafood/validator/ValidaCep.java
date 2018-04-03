package br.com.alura.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.alura.alurafood.formatter.FormataCep;
import br.com.alura.alurafood.formatter.Formatador;

public class ValidaCep extends ValidadorPadrao {

    private static final int DIGITOS_NECESSARIOS = 8;
    private static final String LIMITE_DIGITOS = "CEP deve ter " + DIGITOS_NECESSARIOS + " dígitos";
    private static final int DIGITOS_COM_MASCARA = 9;
    private static final int DIGITOS_SEM_MASCARA = 8;
    private final Formatador formatador = new FormataCep();

    public ValidaCep(TextInputLayout textInputLayout){
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    private ValidaCep(EditText campo) {
        super(campo);
        campo.setOnFocusChangeListener(configuraEstadoDeFoco());
        setEmValidacao(adicionaValidacao());
        setEstadoDeValidacao(configuraEstadoDeValidacao());
    }

    @NonNull
    private View.OnFocusChangeListener configuraEstadoDeFoco() {
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
        return cep -> {
            String cepSemMascara = formatador.semMascara(cep);
            if (naoTemDigitosNecessarios(cepSemMascara)) {
                erro = LIMITE_DIGITOS;
                return false;
            }
            return true;
        };
    }

    @NonNull
    private EstadoDeValidacao configuraEstadoDeValidacao() {
        return new EstadoDeValidacao() {
            @Override
            public void estaValido(String cep) {
                removeMascara(cep);
                mostraMascara(cep);
            }

            @Override
            public void naoEstaValido(String cep) {
                removeMascara(cep);
            }
        };
    }

    private void removeMascara(String cep) {
        String cepSemMascara = formatador.semMascara(cep);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DIGITOS_SEM_MASCARA)});
        campo.setText(cepSemMascara);
    }

    private void mostraMascara(String cep) {
        String cepComMascara = formatador.comMascara(cep);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DIGITOS_COM_MASCARA)});
        campo.setText(cepComMascara);
    }

    public String cepSemMascara() {
        return formatador.semMascara(campo.getText().toString());
    }

    private boolean naoTemDigitosNecessarios(String cepSemMascara) {
        return cepSemMascara.length() != DIGITOS_NECESSARIOS;
    }

}

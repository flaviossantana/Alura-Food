package br.com.alura.alurafood.validator;

import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.widget.EditText;

import br.com.alura.alurafood.formatter.FormataCep;
import br.com.alura.alurafood.formatter.Formatador;

public class ValidaCep extends ValidadorPadrao {

    public static final String DIGITOS_MINIMOS = "CEP precisa de 8 dígitos";
    private Formatador formatador = new FormataCep();

    public ValidaCep(EditText campo) {
        super(campo);
        setEmValidacao(adicionaValidacao());
        setEstadoDeValidacao(configuraEstadoDeValidacao());
    }

    @NonNull
    private EmValidacao adicionaValidacao() {
        return texto -> {
            if (texto.length() < 8) {
                erro = DIGITOS_MINIMOS;
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
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        campo.setText(cepSemMascara);
    }

    private void mostraMascara(String cep) {
        String cepComMascara = formatador.comMascara(cep);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        campo.setText(cepComMascara);
    }

    public String cepSemMascara() {
        return formatador.semMascara(campo.getText().toString());
    }
}

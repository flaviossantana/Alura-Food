package br.com.alura.alurafood.validator;

import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.widget.EditText;

import br.com.alura.alurafood.formatter.FormataNumeroCartaoDeCredito;
import br.com.alura.alurafood.formatter.Formatador;
import br.com.alura.alurafood.util.BandeiraUtil;
import br.com.moip.validators.CreditCard;

public class ValidaNumeroCartao extends ValidadorPadrao {

    private static final String CARTAO_INVALIDO = "Número de cartão de crédito inválido";
    private static final String LIMITE_CARACTERES = "Precisa ter entre 14 a 16 dígitos";
    private final Formatador formatador = new FormataNumeroCartaoDeCredito();

    public ValidaNumeroCartao(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    private ValidaNumeroCartao(EditText campo) {
        super(campo);
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            String numero = campo.getText().toString();
            if(hasFocus){
                removeMascara(numero);
            } else if (super.valida()){
                mostraMascara(numero);
            }
        });
        setEmValidacao(numero -> {

            String numeroSemMascara = formatador.semMascara(numero);
            if (numeroSemMascara.length() < 14 && numeroSemMascara.length() < 17) {
                erro = LIMITE_CARACTERES;
                return false;
            }

            CreditCard cartaoDeCredito = new CreditCard(numeroSemMascara);
            if (!cartaoDeCredito.isValid()) {
                erro = CARTAO_INVALIDO;
                return false;
            }

            return true;
        });
        setEstadoDeValidacao(new EstadoDeValidacao() {
            @Override
            public void estaValido(String numero) {
                String numeroSemMascara = formatador.semMascara(numero);
                CreditCard cartaoDeCredito = new CreditCard(numeroSemMascara);
                mostraMascara(numeroSemMascara);
                int icone = 0;
                try {
                    icone = BandeiraUtil.icone(cartaoDeCredito.getBrand());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                campo.setCompoundDrawablesWithIntrinsicBounds(0, 0, icone, 0);
            }

            @Override
            public void naoEstaValido(String numero) {
                removeMascara(numero);
                campo.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });
    }

    private void mostraMascara(String numero) {
        String numeroComMascara = formatador.comMascara(numero);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(19)});
        campo.setText(numeroComMascara);
    }

    private void removeMascara(String numero) {
        String numeroSemMascara = formatador.semMascara(numero);
        campo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        campo.setText(numeroSemMascara);
    }


}

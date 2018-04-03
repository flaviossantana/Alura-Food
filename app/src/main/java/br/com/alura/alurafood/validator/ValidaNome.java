package br.com.alura.alurafood.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidaNome extends ValidadorPadrao {

    private static final String RESTRICAO_DIGITOS = "Nome pode ter letras e um espaço entre elas";
    private static final String PADRAO_LETRAS_COM_OU_SEM_ACENTO = "[A-ZÀ-Ü]([a-zà-ü])*(\\s[A-ZÀ-Ü]([a-zà-ü])*)*";

    public ValidaNome(TextInputLayout textInputLayout) {
        this(textInputLayout.getEditText());
        this.textInputLayout = textInputLayout;
    }

    private ValidaNome(EditText campo) {
        super(campo);
        setEmValidacao(nome -> {
            if (!nome.matches(PADRAO_LETRAS_COM_OU_SEM_ACENTO)) {
                erro = RESTRICAO_DIGITOS;
                return false;
            }
            return true;
        });
    }
}

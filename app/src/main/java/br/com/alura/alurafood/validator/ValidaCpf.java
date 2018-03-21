package br.com.alura.alurafood.validator;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;

/**
 * Created by alexf on 21/03/18.
 */

public class ValidaCpf implements Validador {

    public static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    public static final String LIMITE_CARACTERES = "CPF deve ter 11 dígitos";
    public static final String INVALIDO = "CPF inválido";
    private String erro;

    @Override
    public boolean valida(String cpf) {

        if (cpf.isEmpty()) {
            erro = CAMPO_OBRIGATORIO;
            return false;
        }

        if (cpf.length() != 11) {
            erro = LIMITE_CARACTERES;
            return false;
        }

        CPFValidator validador = new CPFValidator();
        List<ValidationMessage> erros = validador.invalidMessagesFor(cpf);
        if (!erros.isEmpty()) {
            erro = INVALIDO;
            return false;
        }

        return true;
    }

    @Override
    public String getErro() {
        return erro;
    }
}

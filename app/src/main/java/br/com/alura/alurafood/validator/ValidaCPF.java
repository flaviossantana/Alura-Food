package br.com.alura.alurafood.validator;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class ValidaCPF implements ValidaEditText {

    public boolean invalido(String cpf) {
        return !valida(cpf);
    }

    @Override
    public boolean valida(String cpf) {
        CPFValidator validador = new CPFValidator();
        List<ValidationMessage> erros = validador.invalidMessagesFor(cpf);
        return erros.isEmpty();
    }
}

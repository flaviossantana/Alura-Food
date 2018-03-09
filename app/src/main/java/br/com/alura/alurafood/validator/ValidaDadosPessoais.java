package br.com.alura.alurafood.validator;

import android.widget.EditText;

public class ValidaDadosPessoais {

    private EditText campoNome;
    private EditText campoCpf;
    private EditText campoTelefone;
    private EditText campoEmail;
    private EditText campoSenha;

    public ValidaDadosPessoais(EditText campoNome,
                               EditText campoCpf,
                               EditText campoTelefone,
                               EditText campoEmail) {
        this.campoNome = campoNome;
        this.campoCpf = campoCpf;
        this.campoTelefone = campoTelefone;
        this.campoEmail = campoEmail;
    }

    public boolean valida() {
        return validaCampos();
    }

    private boolean validaCampos() {
        return nomeValido() & cpfValido() &
                telefoneValido() & validaEmail();
    }

    private boolean telefoneValido() {
        String telefone = campoTelefone.getText().toString();
        if (new ValidaTelefone().invalido(telefone)) {
            campoTelefone.setError("Telefone inválido");
            return false;
        }
        return true;
    }

    private boolean cpfValido() {
        String cpf = campoCpf.getText().toString();
        if (new ValidaCPF().invalido(cpf)) {
            campoCpf.setError("Cpf inválido");
            return false;
        }
        return true;
    }

    private boolean nomeValido() {
        String nome = campoNome.getText().toString();
        if (nome.isEmpty()) {
            campoNome.setError("Nome não pode ser vazio");
            return false;
        }
        return true;
    }

    private boolean validaEmail() {
        String email = campoEmail.getText().toString();
        if (new ValidaEmail().invalido(email)) {
            campoEmail.setError("Email inválido");
            return false;
        }
        return true;
    }

    private boolean validaSenha(){

        return true;
    }


}
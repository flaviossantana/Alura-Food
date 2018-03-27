package br.com.alura.alurafood.ui.view;

import android.support.design.widget.TextInputLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.validator.ValidaCpf;
import br.com.alura.alurafood.validator.ValidaEmail;
import br.com.alura.alurafood.validator.ValidaTelefone;
import br.com.alura.alurafood.validator.Validador;
import br.com.alura.alurafood.validator.ValidadorPadrao;

public class DadosPessoaisView {

    private TextInputLayout textInputNome;
    private TextInputLayout textInputCPF;
    private TextInputLayout textInputTelefone;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputSenha;
    private View view;
    private List<Validador> validadores = new ArrayList<>();

    public DadosPessoaisView(View view) {
        this.view = view;
        camposDadosPessoais();
        configuraListenersCamposDadosPessoais();
    }

    private void camposDadosPessoais() {
        textInputNome = view.findViewById(R.id.dados_pessoais_nome);
        textInputCPF = view.findViewById(R.id.dados_pessoais_cpf);
        textInputTelefone = view.findViewById(R.id.dados_pessoais_telefone);
        textInputEmail = view.findViewById(R.id.dados_pessoais_email);
        textInputSenha = view.findViewById(R.id.dados_pessoais_senha);
    }

    private void configuraListenersCamposDadosPessoais() {
        validadores.addAll(Arrays.asList(
                new ValidadorPadrao(textInputNome),
                new ValidaCpf(textInputCPF),
                new ValidaTelefone(textInputTelefone),
                new ValidaEmail(textInputEmail),
                new ValidadorPadrao(textInputSenha)));
    }

    public boolean camposValido() {
        boolean valido = true;
        for (Validador validor :
                validadores) {
            if (!validor.valida()) {
                valido = false;
            }
        }
        return valido;
    }

}

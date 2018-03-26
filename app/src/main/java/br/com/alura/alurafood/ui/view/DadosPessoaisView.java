package br.com.alura.alurafood.ui.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.formatter.FormataCpf;
import br.com.alura.alurafood.formatter.Formatador;
import br.com.alura.alurafood.formatter.formataTelefone;
import br.com.alura.alurafood.validator.ValidaCpf;
import br.com.alura.alurafood.validator.ValidaEmail;
import br.com.alura.alurafood.validator.ValidaNome;
import br.com.alura.alurafood.validator.ValidaSenha;
import br.com.alura.alurafood.validator.ValidaTelefone;
import br.com.alura.alurafood.validator.Validador;

public class DadosPessoaisView {

    private TextInputLayout textInputNome;
    private TextInputLayout textInputCPF;
    private TextInputLayout textInputTelefone;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputSenha;
    private View view;

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
        EditText campoNome = textInputNome.getEditText();
        campoNome.setOnFocusChangeListener(validaNome(campoNome));
        EditText campoCpf = textInputCPF.getEditText();
        campoCpf.setOnFocusChangeListener(validaCpf(campoCpf));
        EditText campoTelefone = textInputTelefone.getEditText();
        campoTelefone.setOnFocusChangeListener(validaTelefone(campoTelefone));
        EditText campoEmail = textInputEmail.getEditText();
        campoEmail.setOnFocusChangeListener(validaEmail(campoEmail));
        EditText campoSenha = textInputSenha.getEditText();
        campoSenha.setOnFocusChangeListener(validaSenha(campoSenha));
    }

    private View.OnFocusChangeListener validaNome(EditText campoNome) {
        Validador validador = new ValidaNome();
        return (v, hasFocus) -> {
            String nome = campoNome.getText().toString();
            if (!hasFocus) {
                if (validador.valida(nome)) {
                    textInputNome.setErrorEnabled(false);
                    textInputNome.setError(null);
                } else {
                    textInputNome.setError(validador.getErro());
                }
            }
        };
    }

    @NonNull
    private View.OnFocusChangeListener validaEmail(EditText campoEmail) {
        Validador validador = new ValidaEmail();
        return (v, hasFocus) -> {
            String email = campoEmail.getText().toString();
            if (!hasFocus) {
                if (validador.valida(email)) {
                    textInputEmail.setErrorEnabled(false);
                    textInputEmail.setError(null);
                } else {
                    textInputEmail.setError(validador.getErro());
                }
            }
        };
    }

    @NonNull
    private View.OnFocusChangeListener validaTelefone(EditText campoTelefone) {
        Validador validador = new ValidaTelefone();
        Formatador formatador = new formataTelefone();
        return (v, hasFocus) -> {
            String telefone = campoTelefone.getText().toString();
            if (hasFocus) {
                String telefoneSemMascara = formatador.semMascara(telefone);
                campoTelefone.setText(telefoneSemMascara);
                campoTelefone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
            } else {
                if (validador.valida(telefone)) {
                    campoTelefone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                    String telefoneComMascara = formatador.comMascara(telefone);
                    campoTelefone.setText(telefoneComMascara);
                    textInputTelefone.setErrorEnabled(false);
                    textInputTelefone.setError(null);
                } else {
                    textInputTelefone.setError(validador.getErro());
                }
            }
        };
    }

    @NonNull
    private View.OnFocusChangeListener validaCpf(EditText campoCpf) {
        Validador validador = new ValidaCpf();
        Formatador formatador = new FormataCpf();
        return (v, hasFocus) -> {
            String cpf = campoCpf.getText().toString();
            if (hasFocus) {
                campoCpf.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
                String cpfSemMascara = formatador.semMascara(cpf);
                campoCpf.setText(cpfSemMascara);
            } else {
                if (validador.valida(cpf)) {
                    campoCpf.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                    String cpfComMascara = formatador.comMascara(cpf);
                    campoCpf.setText(cpfComMascara);
                    textInputCPF.setErrorEnabled(false);
                    textInputCPF.setError(null);
                } else {
                    String cpfSemMascara = formatador.semMascara(cpf);
                    campoCpf.setText(cpfSemMascara);
                    textInputCPF.setError(validador.getErro());
                }
            }
        };
    }

    private View.OnFocusChangeListener validaSenha(EditText campoSenha) {
        ValidaSenha validador = new ValidaSenha();
        return (v, hasFocus) -> {
            String senha = campoSenha.getText().toString();
            if(!hasFocus){
                if(validador.valida(senha)){
                    textInputSenha.setError(null);
                    textInputSenha.setErrorEnabled(false);
                } else {
                    textInputSenha.setError(validador.getErro());
                }
            }
        };
    }

}

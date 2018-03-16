package br.com.alura.alurafood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.model.Endereco;
import br.com.alura.alurafood.retrofit.RetrofitInicializador;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioCadastroActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro";

    private TextInputLayout textInputNome;
    private TextInputLayout textInputCPF;
    private TextInputLayout textInputTelefone;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputSenha;

    private TextInputLayout textInputCep;
    private TextInputLayout textInputLogradouro;
    private TextInputLayout textInputNumero;
    private TextInputLayout textInputBairro;
    private TextInputLayout textInputCidade;
    private TextInputLayout textInputUf;
    private TextInputLayout textInputComplemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        configuraListenersCamposDadosPessoais();
        configuraListenersCamposDadosEndereco();
    }

    private void configuraListenersCamposDadosEndereco() {
        EditText campoCep = textInputCep.getEditText();
        campoCep.setOnFocusChangeListener((v, hasFocus) -> {
                    String cep = campoCep.getText().toString();
                    Toast.makeText(this, "" + cep.length(), Toast.LENGTH_SHORT).show();
                    if (!cep.isEmpty() && cep.length() == 8) {
                        Call<Endereco> call = new RetrofitInicializador()
                                .getEnderecoService()
                                .busca(cep);
                        call.enqueue(new Callback<Endereco>() {
                            @Override
                            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                                Endereco endereco = response.body();
                                Toast.makeText(FormularioCadastroActivity.this,
                                        endereco.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Endereco> call, Throwable t) {
                            }
                        });
                    }
                }
        );

    }

    private void configuraListenersCamposDadosPessoais() {
        EditText campoCpf = textInputCPF.getEditText();
        campoCpf.setOnFocusChangeListener((v, hasFocus) -> {
            String cpf = campoCpf.getText().toString();
            String cpfSemMascara = cpf.replaceAll("[\\.\\-]", "");
            campoCpf.setText(cpfSemMascara);
            if (cpf.length() == 11) {
                CPFFormatter cpfFormatter = new CPFFormatter();
                CPFValidator validador = new CPFValidator();
                List<ValidationMessage> erros = validador.invalidMessagesFor(cpfSemMascara);
                if (erros.isEmpty()) {
                    textInputCPF.setError("Cpf inválido");
                } else {
                    String cpfFormatado = cpfFormatter.format(cpfSemMascara);
                    campoCpf.setText(cpfFormatado);
                    textInputCPF.setError(null);
                    textInputCPF.setErrorEnabled(false);
                }
            } else if (!hasFocus) {
                textInputCPF.setError("Cpf precisa ter 11 dígitos");
            }
        });
        EditText campoTelefone = textInputTelefone.getEditText();
        campoTelefone.setOnFocusChangeListener((v, hasFocus) -> {
            String telefone = campoTelefone.getText().toString();

            String mascaraTelefone = "(\\d{2})(\\d{4,5})(\\d{4})";
            String telefoneSemMascara = telefone.replaceAll("[ \\(\\)\\-]", "");

            campoTelefone.setText(telefoneSemMascara);
            if (telefoneSemMascara.length() > 9 && telefoneSemMascara.length() < 12) {
                String telefoneComMascara = telefoneSemMascara.replaceAll(mascaraTelefone, "($1) $2-$3");
                campoTelefone.setText(telefoneComMascara);
                textInputTelefone.setError(null);
                textInputTelefone.setErrorEnabled(false);
            } else if (!hasFocus) {
                textInputTelefone.setError("Telefone precisa ter 10 a 11 dígitos");
            }
        });

        EditText campoEmail = textInputEmail.getEditText();
        campoEmail.setOnFocusChangeListener((v, hasFocus) -> {
            String PADRAO_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                    "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                    "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\" +
                    "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)" +
                    "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

            String email = campoEmail.getText().toString();
            if (!email.isEmpty()) {
                boolean emailValido = email.matches(PADRAO_EMAIL);
                if (!emailValido) {
                    textInputEmail.setError("Email inválido");
                } else {
                    textInputEmail.setErrorEnabled(false);
                    textInputEmail.setError(null);
                }
            }
        });
    }

    private void inicializaCampos() {
        camposDadosPessoais();
        camposDadosEndereco();
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        Button button = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        button.setOnClickListener(v -> {
            Intent vaiParaPagamento = new Intent(this, FormularioPagamentoActivity.class);
            startActivity(vaiParaPagamento);
        });
    }

    private void camposDadosPessoais() {
        textInputNome = findViewById(R.id.dados_pessoais_nome);
        textInputCPF = findViewById(R.id.dados_pessoais_cpf);
        textInputTelefone = findViewById(R.id.dados_pessoais_telefone);
        textInputEmail = findViewById(R.id.dados_pessoais_email);
        textInputSenha = findViewById(R.id.dados_pessoais_senha);
    }

    private void camposDadosEndereco() {
        textInputCep = findViewById(R.id.dados_endereco_cep);
        textInputLogradouro = findViewById(R.id.dados_endereco_logradouro);
        textInputNumero = findViewById(R.id.dados_endereco_numero);
        textInputBairro = findViewById(R.id.dados_endereco_bairro);
        textInputCidade = findViewById(R.id.dados_endereco_cidade);
        textInputUf = findViewById(R.id.dados_endereco_uf);
        textInputComplemento = findViewById(R.id.dados_endereco_complemento);
    }

}

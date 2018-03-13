package br.com.alura.alurafood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.ui.mascara.CpfMascara;

public class FormularioCadastroActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro";
    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText email;
    private EditText senha;

    private EditText cep;
    private EditText logradouro;
    private EditText numero;
    private EditText bairro;
    private EditText cidade;
    private EditText uf;
    private EditText complemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
    }

    private void inicializaCampos() {
        camposDadosPessoais();
        camposDadosEndereco();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.formulario_cadastro_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.formulario_cadastro_salva) {
            Intent vaiParaPagamento = new Intent(this, FormularioPagamentoActivity.class);
            startActivity(vaiParaPagamento);
        }
        return super.onOptionsItemSelected(item);
    }

    private void camposDadosPessoais() {
        TextInputLayout textInputNome = findViewById(R.id.dados_pessoais_nome);
        nome = textInputNome.getEditText();
        TextInputLayout textInputCPF = findViewById(R.id.dados_pessoais_cpf);
        cpf = textInputCPF.getEditText();
        cpf.addTextChangedListener(new CpfMascara());
        TextInputLayout textInputTelefone = findViewById(R.id.dados_pessoais_telefone);
        telefone = textInputTelefone.getEditText();
        TextInputLayout textInputEmail = findViewById(R.id.dados_pessoais_email);
        email = textInputEmail.getEditText();
        TextInputLayout textInputSenha = findViewById(R.id.dados_pessoais_senha);
        senha = textInputSenha.getEditText();
    }

    private void camposDadosEndereco() {
        TextInputLayout textInputCep = findViewById(R.id.dados_endereco_cep);
        cep = textInputCep.getEditText();
        TextInputLayout textInputLogradouro = findViewById(R.id.dados_endereco_logradouro);
        logradouro = textInputLogradouro.getEditText();
        TextInputLayout textInputNumero = findViewById(R.id.dados_endereco_numero);
        numero = textInputNumero.getEditText();
        TextInputLayout textInputBairro = findViewById(R.id.dados_endereco_bairro);
        bairro = textInputBairro.getEditText();
        TextInputLayout textInputCidade = findViewById(R.id.dados_endereco_cidade);
        cidade = textInputCidade.getEditText();
        TextInputLayout textInputUf = findViewById(R.id.dados_endereco_uf);
        uf = textInputUf.getEditText();
        TextInputLayout textInputComplemento = findViewById(R.id.dados_endereco_complemento);
        complemento = textInputComplemento.getEditText();
    }

}

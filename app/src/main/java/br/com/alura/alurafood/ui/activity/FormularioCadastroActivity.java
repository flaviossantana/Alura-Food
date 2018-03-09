package br.com.alura.alurafood.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.alurafood.R;

public class FormularioCadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private Button botaoCadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        inicializaCampos();
    }

    private void inicializaCampos() {
        TextInputLayout textInputNome = findViewById(R.id.formulario_dados_pessoais_nome);
        nome = textInputNome.getEditText();
        TextInputLayout textInputCPF = findViewById(R.id.formulario_dados_pessoais_cpf);
        cpf = textInputCPF.getEditText();
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_dados_pessoais_telefone);
        telefone = textInputTelefone.getEditText();
        TextInputLayout textInputEmail = findViewById(R.id.formulario_dados_pessoais_email);
        email = textInputEmail.getEditText();
        TextInputLayout textInputSenha = findViewById(R.id.formulario_dados_pessoais_senha);
        senha = textInputSenha.getEditText();
        botaoCadastra = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}

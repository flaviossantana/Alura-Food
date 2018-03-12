package br.com.alura.alurafood.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import br.com.alura.alurafood.R;

public class FormularioPagamentoActivity extends AppCompatActivity {

    private EditText numeroCartao;
    private EditText mesEAno;
    private EditText cvc;
    private EditText nomeNoCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        inicializaCampos();
    }

    private void inicializaCampos() {
        TextInputLayout textInputLayoutNumeroCartao = findViewById(R.id.dados_pagamento_nome_no_cartao);
        numeroCartao = textInputLayoutNumeroCartao.getEditText();
        TextInputLayout textInputLayoutMesEAno = findViewById(R.id.dados_pagamento_mes_e_ano);
        mesEAno = textInputLayoutMesEAno.getEditText();
        TextInputLayout textInputLayoutCvc = findViewById(R.id.dados_pagamento_cvc);
        cvc = textInputLayoutCvc.getEditText();
        TextInputLayout textInputLayoutNomeNoCartao = findViewById(R.id.dados_pagamento_nome_no_cartao);
        nomeNoCartao = textInputLayoutNomeNoCartao.getEditText();
    }
}

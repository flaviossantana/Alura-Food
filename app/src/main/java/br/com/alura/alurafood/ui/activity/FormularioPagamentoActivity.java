package br.com.alura.alurafood.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.alurafood.R;

public class FormularioPagamentoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pagamento";
    private EditText numeroCartao;
    private EditText mesEAno;
    private EditText cvc;
    private EditText nomeNoCartao;
    private Button botaoFinalizaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO_APPBAR);
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
        botaoFinalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);
        botaoFinalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaCompraRealizada = new Intent(FormularioPagamentoActivity.this, ResumoCompraActivity.class);
                startActivity(vaiParaCompraRealizada);
            }
        });
    }

}

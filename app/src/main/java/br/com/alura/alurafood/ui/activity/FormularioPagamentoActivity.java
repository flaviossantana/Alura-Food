package br.com.alura.alurafood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.validator.ValidaNumeroCartao;
import br.com.alura.alurafood.validator.Validador;
import br.com.alura.alurafood.validator.ValidadorPadrao;

public class FormularioPagamentoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Pagamento";
    private TextInputLayout textInputLayoutNumeroCartao;
    private TextInputLayout textInputLayoutAnoCartao;
    private TextInputLayout textInputLayoutMesCartao;
    private TextInputLayout textInputLayoutCvcCartao;
    private TextInputLayout textInputLayoutNomeNoCartao;
    private final List<Validador> validadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        adicionaValidadores();
    }

    private void adicionaValidadores() {
        validadores.addAll(Arrays.asList(
                new ValidaNumeroCartao(textInputLayoutNumeroCartao),
                new ValidadorPadrao(textInputLayoutAnoCartao),
                new ValidadorPadrao(textInputLayoutMesCartao),
                new ValidadorPadrao(textInputLayoutCvcCartao),
                new ValidadorPadrao(textInputLayoutNomeNoCartao)
        ));
    }

    private void inicializaCampos() {
        textInputLayoutNumeroCartao = findViewById(R.id.dados_pagamento_numero_cartao);
        textInputLayoutAnoCartao = findViewById(R.id.dados_pagamento_ano);
        textInputLayoutMesCartao = findViewById(R.id.dados_pagamento_mes);
        textInputLayoutCvcCartao = findViewById(R.id.dados_pagamento_cvc);
        textInputLayoutNomeNoCartao = findViewById(R.id.dados_pagamento_nome_no_cartao);
        Button botaoFinalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);
        configuraBotaoFinalizaCompra(botaoFinalizaCompra);
    }

    private void configuraBotaoFinalizaCompra(Button botaoFinalizaCompra) {
        botaoFinalizaCompra.setOnClickListener(v -> {
            if (camposValido()) {
                abreCompraRealizada();
            }
        });
    }

    private void abreCompraRealizada() {
        Intent vaiParaCompraRealizada = new Intent(FormularioPagamentoActivity.this, ResumoCompraActivity.class);
        startActivity(vaiParaCompraRealizada);
    }

    private boolean camposValido() {
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

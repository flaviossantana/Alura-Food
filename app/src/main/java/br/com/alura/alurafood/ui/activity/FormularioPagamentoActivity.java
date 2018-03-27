package br.com.alura.alurafood.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.util.BandeiraUtil;
import br.com.moip.validators.CreditCard;

public class FormularioPagamentoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Pagamento";
    private Button botaoFinalizaCompra;
    private TextInputLayout textInputLayoutNumeroCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        EditText campoNumeroCartao = textInputLayoutNumeroCartao.getEditText();
        campoNumeroCartao.setOnFocusChangeListener((v, hasFocus) -> {
            String mascaraNumeroCartao = "(\\d{4})(\\d{4})(\\d{4})(\\d{2,4})";
            String numeroCartao = campoNumeroCartao.getText().toString();
            String numeroCartaoSemMascara = numeroCartao.replaceAll(" ", "");
            String numeroCartaoComMascara = numeroCartaoSemMascara;

            if (hasFocus) {
                campoNumeroCartao.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
                campoNumeroCartao.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                campoNumeroCartao.setText(numeroCartaoSemMascara);
            }

            if (numeroCartao.length() > 13 && numeroCartao.length() < 17) {
                CreditCard cartaoDeCredito = new CreditCard(numeroCartao);
                int icone = 0;
                try {
                    icone = BandeiraUtil.icone(cartaoDeCredito.getBrand());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                campoNumeroCartao.setCompoundDrawablesWithIntrinsicBounds(0, 0, icone, 0);
                numeroCartaoComMascara = numeroCartao.replaceAll(mascaraNumeroCartao, "$1 $2 $3 $4");
                campoNumeroCartao.setText(numeroCartaoComMascara);
                textInputLayoutNumeroCartao.setError(null);
                textInputLayoutNumeroCartao.setErrorEnabled(false);
            } else if (!hasFocus) {
                textInputLayoutNumeroCartao.setError("Número de cartão precisa ter pelo menos 14 dígitos");
            }

            if (!hasFocus) {
                campoNumeroCartao.setFilters(new InputFilter[]{new InputFilter.LengthFilter(19)});
                campoNumeroCartao.setText(numeroCartaoComMascara);
            }
        });
    }

    private void inicializaCampos() {
        textInputLayoutNumeroCartao = findViewById(R.id.dados_pagamento_numero_cartao);
        botaoFinalizaCompra = findViewById(R.id.pagamento_botao_finaliza_compra);
        botaoFinalizaCompra.setOnClickListener(v -> {
            Intent vaiParaCompraRealizada = new Intent(FormularioPagamentoActivity.this, ResumoCompraActivity.class);
            startActivity(vaiParaCompraRealizada);
        });
    }

}

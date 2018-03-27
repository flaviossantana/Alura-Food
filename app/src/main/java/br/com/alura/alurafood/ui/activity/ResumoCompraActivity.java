package br.com.alura.alurafood.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.alura.alurafood.R;

public class ResumoCompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        getSupportActionBar().hide();
    }
}

package br.com.alura.alurafood.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.ui.view.DadosEnderecoView;
import br.com.alura.alurafood.ui.view.DadosPessoaisView;

public class FormularioCadastroActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro";
    private DadosPessoaisView dadosPessoaisView;
    private DadosEnderecoView dadosEnderecoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        setTitle(TITULO_APPBAR);
        View viewDecorada = getWindow().getDecorView();
        inicializaCampos(viewDecorada);
    }

    private void inicializaCampos(View view) {
        dadosPessoaisView = new DadosPessoaisView(view);
        dadosEnderecoView = new DadosEnderecoView(view);
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        Button button = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        button.setOnClickListener(v -> {
            if (dadosEnderecoView.camposValido() & dadosPessoaisView.camposValido()) {

            }
//            Intent vaiParaPagamento = new Intent(this, FormularioPagamentoActivity.class);
//            startActivity(vaiParaPagamento);
        });
    }

}

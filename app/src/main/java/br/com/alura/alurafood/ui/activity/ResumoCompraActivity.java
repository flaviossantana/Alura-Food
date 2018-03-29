package br.com.alura.alurafood.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.model.Endereco;
import br.com.alura.alurafood.model.Usuario;

import static br.com.alura.alurafood.util.DataUtil.formataDataBrasileira;
import static br.com.alura.alurafood.util.MoedaUtil.formataMoedaBrasileira;

public class ResumoCompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        getSupportActionBar().hide();
        Endereco endereco = enderecoDeExemplo();
        Usuario usuario = usuarioDeExemplo();
        inicializaCampos(usuario, endereco);
    }

    @NonNull
    private Usuario usuarioDeExemplo() {
        Usuario usuario = new Usuario();
        usuario.setNome("Alex Felipe");
        return usuario;
    }

    @NonNull
    private Endereco enderecoDeExemplo() {
        return new Endereco(
                "04101-300",
                "Rua Vergueiro",
                "", 3185,
                "São Paulo",
                "Vila Mariana",
                "SP");
    }

    private void inicializaCampos(Usuario usuario, Endereco endereco) {
        preencheDataEntrega();
        preencheDestinatario(usuario);
        preencheEndereco(endereco);
        preencheValor();
    }

    private void preencheValor() {
        TextView campoValor = findViewById(R.id.resumo_compra_valor);
        String valorFormatado = formataMoedaBrasileira();
        campoValor.setText(valorFormatado);
    }

    private void preencheEndereco(Endereco endereco) {
        TextView campoEndereco = findViewById(R.id.resumo_compra_endereco);
        campoEndereco.setText(endereco.toString());
    }

    private void preencheDestinatario(Usuario usuario) {
        TextView campoDestinatario = findViewById(R.id.resumo_compra_destinatario);
        campoDestinatario.setText(usuario.getNome());
    }

    private void preencheDataEntrega() {
        TextView campoDataEntrega = findViewById(R.id.resumo_compra_data_de_entrega);
        Calendar hoje = Calendar.getInstance();
        String dataBrasileira = formataDataBrasileira(hoje);
        campoDataEntrega.setText(dataBrasileira);
    }

}

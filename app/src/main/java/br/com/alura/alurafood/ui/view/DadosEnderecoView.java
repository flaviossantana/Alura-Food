package br.com.alura.alurafood.ui.view;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.model.Endereco;
import br.com.alura.alurafood.retrofit.RetrofitInicializador;
import br.com.alura.alurafood.validator.ValidaCep;
import br.com.alura.alurafood.validator.Validador;
import br.com.alura.alurafood.validator.ValidadorPadrao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadosEnderecoView {

    public static final String ENDERECO_NAO_ENCONTRADO = "Endereço não encontrado";
    public static final String FALHA_BUSCA_ENDERECO = "Falha ao buscar endereço";
    private final TextInputLayout textInputCep;
    private final TextInputLayout textInputLogradouro;
    private final TextInputLayout textInputNumero;
    private final TextInputLayout textInputBairro;
    private final TextInputLayout textInputCidade;
    private final TextInputLayout textInputUf;
    private final TextInputLayout textInputComplemento;
    List<Validador> validadores = new ArrayList<>();
    private final ImageView botaoBuscaEndereco;
    private final ProgressBar buscaEnderecoProgressBar;
    private final ConstraintLayout telaFormularioCadastro;

    public DadosEnderecoView(View view) {
        telaFormularioCadastro = view.findViewById(R.id.formulario_cadastro_constraintlayout_principal);
        textInputCep = view.findViewById(R.id.dados_endereco_cep);
        textInputLogradouro = view.findViewById(R.id.dados_endereco_logradouro);
        textInputNumero = view.findViewById(R.id.dados_endereco_numero);
        textInputBairro = view.findViewById(R.id.dados_endereco_bairro);
        textInputCidade = view.findViewById(R.id.dados_endereco_cidade);
        textInputUf = view.findViewById(R.id.dados_endereco_uf);
        textInputComplemento = view.findViewById(R.id.dados_endereco_complemento);
        botaoBuscaEndereco = view.findViewById(R.id.dados_endereco_busca_endereco);
        buscaEnderecoProgressBar = view.findViewById(R.id.dados_endereco_carregando_endereco_progress_bar);
        configuraListenersCamposDadosEndereco();
    }

    private void configuraListenersCamposDadosEndereco() {
        ValidaCep validaCep = new ValidaCep(textInputCep.getEditText());
        validadores.addAll(Arrays.asList(
                validaCep,
                new ValidadorPadrao(textInputLogradouro.getEditText()),
                new ValidadorPadrao(textInputNumero.getEditText()),
                new ValidadorPadrao(textInputBairro.getEditText()),
                new ValidadorPadrao(textInputCidade.getEditText()),
                new ValidadorPadrao(textInputUf.getEditText())));
        configuraBotaoBuscaEndereco(validaCep);
    }

    private void configuraBotaoBuscaEndereco(ValidaCep validador) {
        botaoBuscaEndereco.setOnClickListener(v -> {
            if (validador.valida()) {
                buscaEndereco(validador.cepSemMascara());
            }
        });
    }

    private void buscaEndereco(String cep) {
        buscaEnderecoProgressBar.setVisibility(View.VISIBLE);
        Call<Endereco> call = new RetrofitInicializador()
                .getEnderecoService()
                .busca(cep);
        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                Endereco endereco = response.body();
                if (endereco.getLogradouro() != null) {
                    preenche(endereco);
                } else {
                    Snackbar.make(telaFormularioCadastro,
                            ENDERECO_NAO_ENCONTRADO,
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                }
                buscaEnderecoProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Snackbar.make(telaFormularioCadastro,
                        FALHA_BUSCA_ENDERECO,
                        BaseTransientBottomBar.LENGTH_SHORT).show();
                buscaEnderecoProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void preenche(Endereco endereco) {
        EditText campoLogradouro = textInputLogradouro.getEditText();
        campoLogradouro.setText(endereco.getLogradouro());
        EditText campoBairro = textInputBairro.getEditText();
        campoBairro.setText(endereco.getBairro());
        EditText campoCidade = textInputCidade.getEditText();
        campoCidade.setText(endereco.getCidade());
        EditText campoUF = textInputUf.getEditText();
        campoUF.setText(endereco.getUf());
    }

    public boolean camposValido() {
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

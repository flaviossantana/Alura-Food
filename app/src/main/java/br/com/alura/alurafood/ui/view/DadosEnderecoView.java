package br.com.alura.alurafood.ui.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.formatter.FormataCep;
import br.com.alura.alurafood.formatter.Formatador;
import br.com.alura.alurafood.model.Endereco;
import br.com.alura.alurafood.retrofit.RetrofitInicializador;
import br.com.alura.alurafood.validator.EditTextValidadorPadrao;
import br.com.alura.alurafood.validator.ValidaCep;
import br.com.alura.alurafood.validator.ValidaNumero;
import br.com.alura.alurafood.validator.Validador;
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
        EditTextValidadorPadrao validador = new EditTextValidadorPadrao();
        EditText campoCep = textInputCep.getEditText();
        campoCep.setOnFocusChangeListener(validaCep(campoCep));
        configuraBotaoBuscaEndereco(campoCep);
        EditText campoLogradouro = textInputLogradouro.getEditText();
        EditText campoNumero = textInputNumero.getEditText();
        new ValidaNumero().add(campoNumero);
        EditText campoBairro = textInputBairro.getEditText();
        EditText campoCidade = textInputCidade.getEditText();
        EditText campoUf = textInputUf.getEditText();
        validador.add(campoLogradouro,
                campoBairro, campoCidade, campoUf);
    }

    private void configuraBotaoBuscaEndereco(EditText campoCep) {
        Validador validador = new ValidaCep();
        Formatador formatador = new FormataCep();
        botaoBuscaEndereco.setOnClickListener(v -> {
            String cep = campoCep.getText().toString();
            String cepSemMascara = formatador.semMascara(cep);
            if (validador.valida(cepSemMascara)) {
                campoCep.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                buscaEndereco(cepSemMascara);
                String cepComMascara = formatador.comMascara(cepSemMascara);
                campoCep.setText(cepComMascara);
                campoCep.setError(null);
            } else {
                campoCep.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                campoCep.setText(cepSemMascara);
                String erro = validador.getErro();
                campoCep.setError(erro);
            }
        });
    }

    @NonNull
    private View.OnFocusChangeListener validaCep(EditText campoCep) {
        Validador validador = new ValidaCep();
        Formatador formatador = new FormataCep();
        return (v, hasFocus) -> {
            String cep = campoCep.getText().toString();
            String cepSemMascara = formatador.semMascara(cep);

            if (hasFocus) {
                campoCep.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                campoCep.setText(cepSemMascara);
            } else {
                if (validador.valida(cepSemMascara)) {
                    String cepComMascara = formatador.comMascara(cepSemMascara);
                    campoCep.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                    campoCep.setText(cepComMascara);
                    campoCep.setError(null);
                } else {
                    campoCep.setError(validador.getErro());
                }
            }
        };
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

}

package br.com.alura.alurafood.ui.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.model.Endereco;
import br.com.alura.alurafood.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DadosEnderecoView {

    private TextInputLayout textInputCep;
    private TextInputLayout textInputLogradouro;
    private TextInputLayout textInputNumero;
    private TextInputLayout textInputBairro;
    private TextInputLayout textInputCidade;
    private TextInputLayout textInputUf;
    private TextInputLayout textInputComplemento;
    private View view;

    public DadosEnderecoView(View view) {
        this.view = view;
        camposDadosEndereco();
        configuraListenersCamposDadosEndereco();
    }

    private void camposDadosEndereco() {
        textInputCep = view.findViewById(R.id.dados_endereco_cep);
        textInputLogradouro = view.findViewById(R.id.dados_endereco_logradouro);
        textInputNumero = view.findViewById(R.id.dados_endereco_numero);
        textInputBairro = view.findViewById(R.id.dados_endereco_bairro);
        textInputCidade = view.findViewById(R.id.dados_endereco_cidade);
        textInputUf = view.findViewById(R.id.dados_endereco_uf);
        textInputComplemento = view.findViewById(R.id.dados_endereco_complemento);
    }

    private void configuraListenersCamposDadosEndereco() {
        EditText campoCep = textInputCep.getEditText();
        campoCep.setOnFocusChangeListener(validaCep(campoCep)
        );

    }

    @NonNull
    private View.OnFocusChangeListener validaCep(EditText campoCep) {
        return (v, hasFocus) -> {
                    String cep = campoCep.getText().toString();
                    String mascaraCep = "(\\d{5})(\\d{3})";
                    String cepSemMascara = cep.replaceAll("[-]", "");

                    if (hasFocus) {
                        campoCep.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                        campoCep.setText(cepSemMascara);
                    }

                    if (!hasFocus) {
                        if (!cepSemMascara.isEmpty() && cepSemMascara.length() == 8) {
                            Call<Endereco> call = new RetrofitInicializador()
                                    .getEnderecoService()
                                    .busca(cep);
                            call.enqueue(new Callback<Endereco>() {
                                @Override
                                public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                                    Endereco endereco = response.body();
                                    if (endereco != null) {
                                        preenche(endereco);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Endereco> call, Throwable t) {
                                }
                            });

                            campoCep.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                            String cepComMascara = cepSemMascara.replaceAll(mascaraCep, "$1-$2");
                            campoCep.setText(cepComMascara);
                            textInputCep.setError(null);
                            textInputCep.setErrorEnabled(false);
                        } else {
                            textInputCep.setError("CEP precisa ter 8 dígitos");
                        }
                    }
                };
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

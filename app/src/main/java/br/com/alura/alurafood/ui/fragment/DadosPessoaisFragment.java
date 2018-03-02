package br.com.alura.alurafood.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.ui.viewmodel.FormularioCadastradoViewModel;

public class DadosPessoaisFragment extends Fragment {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewCriada = inflater.inflate(R.layout.formulario_dados_pessoais, container, false);
        FormularioCadastradoViewModel viewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(FormularioCadastradoViewModel.class);
        viewModel.getUsuario().setNome("Alex");
        preencheCampos(viewCriada);
        return viewCriada;
    }

    private void preencheCampos(View view) {
        TextInputLayout textInputNome = view.findViewById(R.id.formulario_dados_pessoais_nome);
        nome = textInputNome.getEditText();
        TextInputLayout textInputCPF = view.findViewById(R.id.formulario_dados_pessoais_cpf);
        cpf = textInputCPF.getEditText();
        TextInputLayout textInputTelefone = view.findViewById(R.id.formulario_dados_pessoais_telefone);
        telefone = textInputTelefone.getEditText();
        TextInputLayout textInputEmail = view.findViewById(R.id.formulario_dados_pessoais_email);
        email = textInputEmail.getEditText();
        TextInputLayout textInputSenha = view.findViewById(R.id.formulario_dados_pessoais_senha);
        email = textInputSenha.getEditText();
    }

}

package br.com.alura.alurafood.ui.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.com.alura.alurafood.R;
import br.com.alura.alurafood.ui.fragment.DadosPessoaisFragment;
import br.com.alura.alurafood.ui.viewmodel.FormularioCadastradoViewModel;

public class FormularioCadastroActivity extends AppCompatActivity {

    private FormularioCadastradoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);
        createViewModel();
        Log.i("viewmodel activity", String.valueOf(viewModel));
        createViewModel();
        Log.i("viewmodel activity", String.valueOf(viewModel));
        apresentaFragment();
    }

    private void createViewModel() {
        ViewModelProvider.AndroidViewModelFactory provider = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication());
        viewModel = provider.create(FormularioCadastradoViewModel.class);
    }

    private void apresentaFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.formulario_cadastro_framelayout, new DadosPessoaisFragment())
                .commit();
    }

}

package br.com.alura.alurafood.ui.viewmodel;

import android.arch.lifecycle.ViewModel;

import br.com.alura.alurafood.model.Usuario;

public class FormularioCadastradoViewModel extends ViewModel {

    private Usuario usuario;

    public Usuario getUsuario() {
        if (usuario == null) {
            this.usuario = new Usuario();
        }
        return this.usuario;
    }


}

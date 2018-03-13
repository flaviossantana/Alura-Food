package br.com.alura.alurafood.ui.mascara;

import android.text.Editable;
import android.text.TextWatcher;

public class CpfMascara implements TextWatcher {

    private static final String CPF = "###.###.###-##";
    private static final String NUMEROS = "1234567890";
    public static final String DIGITO_VALIDO = "#";
    private boolean editando = false;
    private String textoAtualizado = "";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence texto, int indiceTexto, int before, int count) {

        String textoDigitado = texto.toString();

        if (textoDigitado.length() == textoAtualizado.length() || editando) {
            return;
        }

        estaEditando();

        if (estaRemovendoCaracter(textoDigitado)) {
            textoAtualizado = textoDigitado;
            naoEstaEditando();
            return;
        }

        if (temCaracterNovo(indiceTexto)) {
            String caracterDigitado = String.valueOf(textoDigitado.charAt(indiceTexto));
            String caracterDaMascara = String.valueOf(CPF.charAt(indiceTexto));
            if (NUMEROS.contains(caracterDigitado) && caracterDaMascara.equals(DIGITO_VALIDO)) {
                textoAtualizado += caracterDigitado;
            } else if (caracterDaMascara != DIGITO_VALIDO && CPF.contains(caracterDaMascara)) {
                textoAtualizado += caracterDaMascara;
                if (NUMEROS.contains(caracterDigitado)) {
                    textoAtualizado += caracterDigitado;
                }
            }
            naoEstaEditando();
            return;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (editando)
            return;

        estaEditando();
        editable.clear();
        editable.append(textoAtualizado);
        naoEstaEditando();
    }

    private boolean temCaracterNovo(int start) {
        return editando && quantidadePermitida(start);
    }

    private boolean estaRemovendoCaracter(String texto) {
        return texto.length() < textoAtualizado.length();
    }

    private void naoEstaEditando() {
        editando = false;
    }

    private void estaEditando() {
        editando = true;
    }

    private boolean quantidadePermitida(int totalCaracteres) {
        return totalCaracteres < CPF.length();
    }

}

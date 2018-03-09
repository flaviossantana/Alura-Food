package br.com.alura.alurafood.ui.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class MascaraEditText implements TextWatcher {

    private static final String CPF = "###.###.###-##";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        int totalCaracteres = s.length();
        if (totalCaracteres < CPF.length()) {
            char caracter = CPF.charAt(totalCaracteres);
            if (caracter != '#') {
                Log.i("mask", String.valueOf(caracter));
            } else if (CPF.charAt(totalCaracteres) != '#') {
                s.insert(totalCaracteres, CPF, totalCaracteres, totalCaracteres);
            }
        }
    }
}

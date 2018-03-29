package br.com.alura.alurafood.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static br.com.alura.alurafood.util.ConstantesUtil.BRASIL;
import static br.com.alura.alurafood.util.ConstantesUtil.PORTUGUES;

public class MoedaUtil {

    private MoedaUtil() {
    }

    public static String formataMoedaBrasileira() {
        NumberFormat moedaBrasileira = DecimalFormat.getCurrencyInstance(new Locale(PORTUGUES, BRASIL));
        return moedaBrasileira.format(200L);
    }

}

package br.com.alura.alurafood.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static br.com.alura.alurafood.util.ConstantesUtil.BRASIL;
import static br.com.alura.alurafood.util.ConstantesUtil.PORTUGUES;

public class DataUtil {

    private static final String FORMATO_DATA_BRASILEIRA = "dd/MM/yyyy";

    private DataUtil() {
    }

    public static String formataDataBrasileira(Calendar hoje) {
        SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA_BRASILEIRA, new Locale(PORTUGUES, BRASIL));
        return formatador.format(hoje.getTime());
    }
}

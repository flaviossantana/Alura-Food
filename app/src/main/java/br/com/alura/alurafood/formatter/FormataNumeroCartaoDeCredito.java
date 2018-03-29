package br.com.alura.alurafood.formatter;

public class FormataNumeroCartaoDeCredito implements Formatador {

    private static final String MASCARA = "(\\d{4})(\\d{4})(\\d{4})(\\d{2,4})";
    public static final String FORMATO = "$1 $2 $3 $4";
    private static final String CARACTERES_MASCARA = " ";


    @Override
    public String comMascara(String numero) {
        return numero.replaceAll(MASCARA, FORMATO);
    }

    @Override
    public String semMascara(String numero) {
        return numero.replaceAll(CARACTERES_MASCARA, VAZIO);
    }
}

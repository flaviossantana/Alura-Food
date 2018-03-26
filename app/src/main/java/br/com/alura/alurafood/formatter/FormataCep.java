package br.com.alura.alurafood.formatter;

public class FormataCep implements Formatador {

    private static final String MASCARA = "(\\d{5})(\\d{3})";
    public static final String FORMATO = "$1-$2";

    @Override
    public String comMascara(String cepSemMascara) {
        return cepSemMascara.replaceAll(MASCARA, FORMATO);
    }

    @Override
    public String semMascara(String cepComMascara) {
        return cepComMascara.replaceAll("[-]", "");
    }
}

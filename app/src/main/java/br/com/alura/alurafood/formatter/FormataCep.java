package br.com.alura.alurafood.formatter;

public class FormataCep implements Formatador {

    private static final String MASCARA = "(\\d{5})(\\d{3})";
    private static final String FORMATO = "$1-$2";
    private static final String CARACTERES_MASCARA = "[-]";

    @Override
    public String comMascara(String cepSemMascara) {
        return cepSemMascara.replaceAll(MASCARA, FORMATO);
    }

    @Override
    public String semMascara(String cepComMascara) {
        return cepComMascara.replaceAll(CARACTERES_MASCARA, VAZIO);
    }
}

package br.com.alura.alurafood.formatter;

public class FormataTelefone implements Formatador {

    private static final String MASCARA = "(\\d{2})(\\d{4,5})(\\d{4})";
    private static final String FORMATO = "($1) $2-$3";
    private static final String CARACTERES_MASCARA = "[ ()\\-]";

    @Override
    public String comMascara(String telefone) {
        return telefone.replaceAll(MASCARA, FORMATO);
    }

    @Override
    public String semMascara(String telefone) {
        return telefone.replaceAll(CARACTERES_MASCARA, VAZIO);
    }
}

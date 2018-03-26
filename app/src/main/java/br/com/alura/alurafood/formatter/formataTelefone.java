package br.com.alura.alurafood.formatter;

public class formataTelefone implements Formatador {

    private static final String MASCARA_TELEFONE = "(\\d{2})(\\d{4,5})(\\d{4})";
    public static final String FORMATO_TELEFONE = "($1) $2-$3";
    public static final String CARACTERES_MASCARA = "[ \\(\\)\\-]";
    public static final String VAZIO = "";

    @Override
    public String comMascara(String telefone) {
        return telefone.replaceAll(MASCARA_TELEFONE, FORMATO_TELEFONE);
    }

    @Override
    public String semMascara(String telefone) {
        return telefone.replaceAll(CARACTERES_MASCARA, VAZIO);
    }
}

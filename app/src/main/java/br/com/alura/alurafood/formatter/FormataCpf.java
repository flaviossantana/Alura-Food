package br.com.alura.alurafood.formatter;

import br.com.caelum.stella.format.CPFFormatter;

public class FormataCpf implements Formatador {

    @Override
    public String comMascara(String cpfSemMascara) {
        CPFFormatter cpfFormatter = new CPFFormatter();
        return cpfFormatter.format(cpfSemMascara);
    }

    @Override
    public String semMascara(String cpfComMascara) {
        return cpfComMascara.replaceAll("[\\.\\-]", "");
    }
}

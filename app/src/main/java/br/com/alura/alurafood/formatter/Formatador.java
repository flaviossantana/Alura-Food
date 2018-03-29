package br.com.alura.alurafood.formatter;

public interface Formatador {

    String VAZIO = "";

    String comMascara(String textoSemMascara);

    String semMascara(String textoComMascara);
}

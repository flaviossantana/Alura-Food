package br.com.alura.alurafood.model;

import com.google.gson.annotations.SerializedName;

public class Endereco {

    public static final String CHAVE_CIDADE = "localidade";

    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    @SerializedName(CHAVE_CIDADE)
    private String cidade;
    private String bairro;
    private String uf;

    public Endereco() {

    }

    public Endereco(String cep, String logradouro, String complemento, int numero, String cidade, String bairro, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return logradouro + ", " + numero + " - " + bairro + ", " + cidade + " - " + uf + ", " + cep;
    }
}

package br.com.alura.alurafood.validator;

public class ValidaSenha implements ValidaEditText {

    private static final String PELO_MENOS_OITO_DIGITOS = "{8,}";
    private static final String LETRA_MINUSCULA = "(?=.*[a-z].*)";
    private static final String LETRA_MAIUSCULA = "(?=.*[A-Z].*)";
    private static final String NUMEROS = "(?=.*[0-1].*)";
    private static String padraoInicial = "";

    private ValidaSenha() {

    }

    static class Builder {

        public Builder comLetraMinuscula() {
            padraoInicial += LETRA_MINUSCULA;
            return this;
        }

        public Builder comLetraMaiuscula() {
            padraoInicial += LETRA_MAIUSCULA;
            return this;
        }

        public Builder comNumeros() {
            padraoInicial += NUMEROS;
            return this;
        }

        public ValidaSenha build() {
            configuraLimiteDeDigitos();
            return new ValidaSenha();
        }

        private void configuraLimiteDeDigitos() {
            if (padraoInicial.isEmpty()) {
                padraoInicial += ".*" + PELO_MENOS_OITO_DIGITOS;
            } else {
                padraoInicial += "." + PELO_MENOS_OITO_DIGITOS;
            }
        }

    }

    @Override
    public boolean valida(String senha) {
        System.out.println(padraoInicial);
        return senha.matches(padraoInicial);
    }

    public boolean invalido(String senha) {
        return !valida(senha);
    }
}

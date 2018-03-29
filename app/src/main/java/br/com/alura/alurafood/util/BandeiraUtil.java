package br.com.alura.alurafood.util;

import br.com.alura.alurafood.R;
import br.com.moip.creditcard.Brands;

public class BandeiraUtil {

    public static final String BANDEIRA_NAO_CONHECIDA = "Bandeira não conhecida";

    private BandeiraUtil() {
    }

    public static int icone(Brands bandeira) {
        if (bandeira.equals(Brands.MASTERCARD)) {
            return R.drawable.ic_mastercard;
        }
        if (bandeira.equals(Brands.VISA)) {
            return R.drawable.ic_visa;
        }
        if (bandeira.equals(Brands.AMERICAN_EXPRESS)) {
            return R.drawable.ic_amex;
        }
        if (bandeira.equals(Brands.DINERS)) {
            return R.drawable.ic_diners;
        }
        if (bandeira.equals(Brands.HIPERCARD)) {
            return R.drawable.ic_hipercard;
        }
        if (bandeira.equals(Brands.ELO)) {
            return R.drawable.ic_elo;
        }
        throw new IllegalArgumentException(BANDEIRA_NAO_CONHECIDA);
    }

}
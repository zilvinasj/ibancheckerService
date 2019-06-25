/**
 * 
 */
package com.demo.service;

import java.math.BigInteger;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

/**
 * @author ZJankunas
 *
 */
@Service
public class IbanCheckerService
{

    private static final String ALLOWED_CHARACTERS_REGEX = "[0-9A-Z]+";

    public boolean validateIBAN(String iban)
    {

        if (iban.length() < 4 || !iban.matches(ALLOWED_CHARACTERS_REGEX) || validateCountry(iban))
        {
            return false;
        }

        iban = iban.substring(4) + iban.substring(0, 4);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iban.length(); i++)
            sb.append(Character.digit(iban.charAt(i), 36));

        BigInteger bigInt = new BigInteger(sb.toString());

        return bigInt.mod(BigInteger.valueOf(97)).intValue() == 1;
    }

    /**
     * @param iban
     * @return
     */
    private boolean validateCountry(String iban)
    {
        return Stream.of(Locale.getISOCountries()).noneMatch(e -> e.equals(iban.substring(0, 2)));
    }

}

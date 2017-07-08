package com.wpers.codewar;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.BigInteger.*;

public class Anagrams {
    public BigInteger listPosition(String word) {
        BigInteger resultado = ONE;

        ArrayList<Character> cadena = new ArrayList<Character>();
        for (char c : word.toCharArray()) {
            cadena.add(c);
        }
        
        ArrayList<Character> cadenaordenada = new ArrayList<Character>(cadena);
        Collections.sort(cadenaordenada);

        ArrayList<Character> cadenaunica = new ArrayList<Character>();
        cadenaunica = cadena.stream().distinct().sorted().collect(Collectors.toCollection(ArrayList::new));

        int i = 0;
        int j = 0;

        while (i < cadena.size() || j < cadenaunica.size()) {
            if (cadena.get(i).compareTo(cadenaunica.get(j++)) == 0) {
                // Elimina cadena.get(i) de cadenaordenada
                cadenaordenada.remove(cadena.get(i));
                // Genera nuevamente cadenaunica
                cadenaunica = cadenaordenada.stream().distinct().sorted().collect(Collectors.toCollection(ArrayList::new));
                i++;
                j = 0;
            } else {
                ArrayList<Character> cadenaordenadaaux = new ArrayList<Character>(cadenaordenada);
                // Remueve de cadenaordenada cadena.get(i) o cadenaunica.get(j-1) segun valor
                cadenaordenadaaux.remove(cadena.get(i) < cadenaunica.get(j - 1) ? cadena.get(i) : cadenaunica.get(j - 1));
                BigInteger cantidad = getCantidadAnagramas(cadenaordenadaaux);
                resultado = resultado.add(cantidad);
            }
        }

        return resultado;
    }

    private BigInteger getCantidadAnagramas(ArrayList<Character> cadenaordenada) {
        int len = cadenaordenada.size();

        Map<Character, Integer> numChars = new HashMap<Character, Integer>(Math.min(len, 26));
        BigInteger numerador = factorial(cadenaordenada.size());
        setMapa(numChars, cadenaordenada);
        BigInteger denominador =  getDivisor(numChars);

        return numerador.divide(denominador);
    }

    BigInteger getDivisor(Map<Character, Integer> numChars) {
        BigInteger resultado = ONE;

        for (Character c: numChars.keySet()) {
            int cantidad = numChars.get(c);
            resultado = resultado.multiply(factorial(cantidad));
        }

        return resultado;
    }

    public static BigInteger factorial(int n)
    {
        return n > 2 ? new BigInteger(n+"").multiply(factorial(n-1)) : new BigInteger(n+"");
    }

    static void setMapa(Map<Character, Integer> numChars, ArrayList<Character> cadenaordenada) {
        int len = cadenaordenada.size();

        for (int i = 0; i < len; ++i) {
            char charAt = cadenaordenada.get(i);

            if (!numChars.containsKey(charAt)) {
                numChars.put(charAt, 1);
            } else {
                numChars.put(charAt, numChars.get(charAt) + 1);
            }
        }
    }
}


package cl.ciisa.comunica.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Clase para codificar
 * @author cristian
 */
public class Crypt {

    /**
     * Convierte la clave ingresada en n String MD5
     * @param input
     * @return 
     */
    public static String digest(String input) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");
            byte[] array = hash.digest(input.getBytes());
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe algoritmo");
            e.printStackTrace();
        }
        return sb.toString();

    }
    /**
     * Compara la clave ingresada con la clave almacenada en MD5
     * que esta almacenada en la base de datos
     * @param input
     * @param orig
     * @return 
     */
    public static boolean compare(String input, String orig) {
        boolean ok = false;
        ok = input.equals(orig);
        return ok;

    }

}

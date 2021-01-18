package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.InvalidAccessKeyException;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Utilitarios para el manejo de cadenas de texto
 * @author Rolando Rodríguez
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Verifica si una cadena de texto es vacía
     * @param value Cadena de texto
     * @return Verdadero si la cadena de texto es vacía; falso en caso contrario
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Verifica si una cadena de texto no es vacía
     * @param value Cadena de texto
     * @return Falso si la cadena de texto es vacía; verdadero en caso contrario
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * Retorna el número de R.U.C. de una clave de acceso
     * @param accessKey Clave de acceso
     * @return Número de R.U.C.
     */
    public static String getIssuerFromAccessKey(String accessKey) {
        if (isNotEmpty(accessKey) && accessKey.length() == Constants.MAX_ACCESS_KEY_LENGTH) {
            return accessKey.substring(10, 23);
        }
        throw new InvalidAccessKeyException(accessKey);
    }

    /**
     * Retorna el tipo de documento de una clave de acceso
     * @param accessKey Clave de acceso
     * @return Tipo de documento
     */
    public static String getDocumentType(String accessKey) {
        if (isNotEmpty(accessKey) && accessKey.length() == Constants.MAX_ACCESS_KEY_LENGTH) {
            return accessKey.substring(8, 10);
        }
        throw new InvalidAccessKeyException(accessKey);
    }

    /**
     * Retorna el número de documento de una clave de acceso
     * @param accessKey Clave de acceso
     * @return Número de documento
     */
    public static String getDocumentNumber(String accessKey) {
        if (isNotEmpty(accessKey) && accessKey.length() == Constants.MAX_ACCESS_KEY_LENGTH) {
            return accessKey.substring(30, 39);
        }
        throw new InvalidAccessKeyException(accessKey);
    }

    /**
     * Verifica si una dirección de correo electrónico está bien formada
     * @param emailAddress Dirección de correo electrónico
     * @return Verdadero si la dirección de correo electrónico está bien formada; falsa en caso contrario
     */
    public static boolean isValid(String emailAddress) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(emailAddress).matches();
    }

}
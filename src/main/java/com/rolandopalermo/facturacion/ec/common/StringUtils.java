package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.InvalidAccessKeyException;
import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * Utilitarios para el manejo de cadenas de texto
 *
 * @author Rolando Rodríguez
 */
@UtilityClass
public class StringUtils {

    /**
     * Verifica si una cadena de texto es vacía
     *
     * @param value Cadena de texto
     * @return Verdadero si la cadena de texto es vacía; falso en caso contrario
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Verifica si una cadena de texto no es vacía
     *
     * @param value Cadena de texto
     * @return Falso si la cadena de texto es vacía; verdadero en caso contrario
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * Retorna el número de R.U.C. de una clave de acceso
     *
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
     *
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
     * Retorna el establecimiento de una clave de acceso
     *
     * @param accessKey Clave de acceso
     * @return Establecimiento
     */
    public static String getEstablishment(String accessKey) {
        if (isNotEmpty(accessKey) && accessKey.length() == Constants.MAX_ACCESS_KEY_LENGTH) {
            return accessKey.substring(24, 27);
        }
        throw new InvalidAccessKeyException(accessKey);
    }

    /**
     * Retorna el número de documento de una clave de acceso
     *
     * @param accessKey Clave de acceso
     * @return Número de documento
     */
    public static String getDocumentNumber(String accessKey) {
        if (isNotEmpty(accessKey) && accessKey.length() == Constants.MAX_ACCESS_KEY_LENGTH) {
            return accessKey.substring(30, 39);
        }
        throw new InvalidAccessKeyException(accessKey);
    }

    public static String formatSequenceNumber(String value) {
        return StringUtils.isNotEmpty(value) ? String.format("%09d", Integer.parseInt(value)) : "";
    }

    public static String toIdFormat(String string) {
        return string == null ? "" : string.toUpperCase().trim().replaceAll("\\s+", "_");
    }

    public static boolean isValidEmail(String emailAddress) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(emailAddress).matches();
    }

}

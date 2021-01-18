package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por claves de acceso inválidas
 * @author Rolando
 */
public class InvalidAccessKeyException extends VeronicaException {

    public InvalidAccessKeyException(String accessKey) {
        super(String.format("La clave de acceso [%s] es inválida", accessKey));
    }

}
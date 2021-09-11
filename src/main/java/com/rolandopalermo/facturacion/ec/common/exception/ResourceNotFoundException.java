package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por registros no encontrados
 *
 * @author Rolando
 */
public class ResourceNotFoundException extends VeronicaException {

    public ResourceNotFoundException(final String message) {
        super(message);
    }

}

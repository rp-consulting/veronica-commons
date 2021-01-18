package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por estatus internos inválidos
 * @author Rolando
 */
public class InvalidInternalStatusException extends RuntimeException {

    public InvalidInternalStatusException(String currentInternalStatus) {
        super(String.format("El estatus [%s] del comprobante es inválido", currentInternalStatus));
    }

}
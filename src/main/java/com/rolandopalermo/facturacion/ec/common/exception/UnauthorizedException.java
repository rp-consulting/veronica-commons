package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

/**
 * Clase para manejar excepciones lanzadas por acciones no autorizadas
 *
 * @author Rolando
 */
public class UnauthorizedException extends VeronicaException {

    private static final String MESSAGE = "El usuario [{0}] no se encuentra autorizado para realizar esta acción";
    private static final String MESSAGE_WITH_SUPPLIER = "El usuario [{0}] no tiene permiso para operar con el R.U.C. [{1}]";

    public UnauthorizedException(final String username) {
        super(MessageFormat.format(MESSAGE, username));
    }

    public UnauthorizedException(final String username, String supplierNumber) {
        super(MessageFormat.format(MESSAGE_WITH_SUPPLIER, username, supplierNumber));
    }

}

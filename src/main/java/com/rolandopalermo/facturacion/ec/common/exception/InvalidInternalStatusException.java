package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class InvalidInternalStatusException extends VeronicaException {

    private static final String MESSAGE = "El estatus interno [{0}] del comprobante electrónico es inválido para esta acción";

    public InvalidInternalStatusException(final String internalStatus) {
        super(MessageFormat.format(MESSAGE, internalStatus));
    }

}

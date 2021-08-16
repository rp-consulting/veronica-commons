package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class AlreadyExistsException extends VeronicaException {

    private static final String MESSAGE = "El recurso [{0}] ya se encuentra registrado";

    public AlreadyExistsException(final String resourceId) {
        super(MessageFormat.format(MESSAGE, resourceId));
    }

}

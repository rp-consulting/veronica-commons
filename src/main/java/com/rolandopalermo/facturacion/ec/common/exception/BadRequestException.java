package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class BadRequestException extends VeronicaException {

    private static final String MESSAGE = "El recurso [{0}] no pasó el proceso de validación de datos";

    public BadRequestException(final String resource) {
        super(MessageFormat.format(MESSAGE, resource));
    }

}

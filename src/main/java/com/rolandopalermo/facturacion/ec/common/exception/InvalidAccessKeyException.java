package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class InvalidAccessKeyException extends VeronicaException {

    private static final String MESSAGE = "La clave de acceso [{0}] es inválida";

    public InvalidAccessKeyException(final String accessKey) {
        super(MessageFormat.format(MESSAGE, accessKey));
    }

}

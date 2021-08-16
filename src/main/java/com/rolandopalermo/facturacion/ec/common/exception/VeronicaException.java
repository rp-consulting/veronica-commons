package com.rolandopalermo.facturacion.ec.common.exception;

import static java.text.MessageFormat.format;

public class VeronicaException extends RuntimeException {

    public VeronicaException(final String message) {
        super(message);
    }

    public VeronicaException(final String key, final String... args) {
        super(format(key, args));
    }

}

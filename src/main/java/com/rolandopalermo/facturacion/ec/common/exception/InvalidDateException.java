package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class InvalidDateException extends VeronicaException {

    private static final String MESSAGE = "La fecha [{0}] no tiene un formato válido";

    public InvalidDateException(final String dateFormat) {
        super(MessageFormat.format(MESSAGE, dateFormat));
    }

}

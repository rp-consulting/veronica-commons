package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class InvalidDateFormatException extends VeronicaException {

    private static final String MESSAGE = "No existe un formato de fecha válido para [{0}]";

    public InvalidDateFormatException(final String dateFormat) {
        super(MessageFormat.format(MESSAGE, dateFormat));
    }

}

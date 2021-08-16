package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class InvalidDocumentTypeException extends VeronicaException {

    private static final String MESSAGE = "El tipo de documento [{0}] es inválido";

    public InvalidDocumentTypeException(final String docCode) {
        super(MessageFormat.format(MESSAGE, docCode));
    }

}

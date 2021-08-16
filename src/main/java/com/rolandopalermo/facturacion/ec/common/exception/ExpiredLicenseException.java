package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

public class ExpiredLicenseException extends VeronicaException {

    private static final String MESSAGE = "Su licencia ha expirado el [{0}]";

    public ExpiredLicenseException(final String expirationDate) {
        super(MessageFormat.format(MESSAGE, expirationDate));
    }

}

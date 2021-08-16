package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_FORMAT;
import static java.time.format.DateTimeFormatter.ofPattern;

public class InvalidDateRangeException extends VeronicaException {

    private static final DateTimeFormatter DATE_FORMAT_PATTERN = ofPattern(DATE_FORMAT);
    private static final String MESSAGE = "El rango de fechas [{0}-{1}] es inválido";

    public InvalidDateRangeException(final LocalDate startDate, final LocalDate endDate) {
        super(MessageFormat.format(MESSAGE, startDate.format(DATE_FORMAT_PATTERN), endDate.format(DATE_FORMAT_PATTERN)));
    }

}

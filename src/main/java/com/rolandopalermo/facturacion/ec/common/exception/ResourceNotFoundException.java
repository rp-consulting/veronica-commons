package com.rolandopalermo.facturacion.ec.common.exception;

import java.text.MessageFormat;

/**
 * Clase para manejar excepciones lanzadas por registros no encontrados
 *
 * @author Rolando
 */
public class ResourceNotFoundException extends VeronicaException {

    private static final String MESSAGE = "El recurso [{0}] no existe o su estatus es inválido para esta acción";

    public ResourceNotFoundException(final String resourceId) {
        super(MessageFormat.format(MESSAGE, resourceId));
    }

}

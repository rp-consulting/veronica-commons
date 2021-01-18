package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por registros no encontrados
 * @author Rolando
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String object) {
        super(String.format("%s no se encuentra registrado(a) o su estatus es inválido para esta acción", object));
    }

}
package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por tipos de documentos inválidos
 * @author Rolando
 */
public class InvalidDocumentTypeException extends VeronicaException {

    public InvalidDocumentTypeException(String accessKey) {
        super(String.format("El tipo de documento [%s] es inválido", accessKey));
    }

}
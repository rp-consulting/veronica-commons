package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por empresas no autorizadas para operar sobre un
 * determinado número de R.U.C.
 * @author Rolando
 */
public class InvalidIssuerException extends VeronicaException {

    public InvalidIssuerException(String supplierNumber) {
        super(String.format("El emisor con el identificador [%s] no está habilitado para operar sobre el comprobante", supplierNumber));
    }

}
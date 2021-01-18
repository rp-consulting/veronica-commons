package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por acciones no autorizadas
 *
 * @author Rolando
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String accountName) {
        super(String.format("El usuario %s es inválido para ejecutar la acción", accountName));
    }

    public UnauthorizedException(String accountName, String supplierNumber) {
        super(String.format("El usuario %s no tiene licencia para operar con el R.U.C. %s", accountName, supplierNumber));
    }

}
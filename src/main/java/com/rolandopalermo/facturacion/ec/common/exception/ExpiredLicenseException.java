package com.rolandopalermo.facturacion.ec.common.exception;

/**
 * Clase para manejar excepciones lanzadas por licencias expiradas
 * @author Rolando
 */
public class ExpiredLicenseException extends VeronicaException {

    public ExpiredLicenseException(String dueDate) {
        super(String.format("Su licencia expiró el %s", dueDate));
    }

}
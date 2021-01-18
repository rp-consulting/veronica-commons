package com.rolandopalermo.facturacion.ec.common.interfaces;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;

/**
 * Interfaz para definir si una clase soporta un tipo de documento específico
 * @author Rolando
 */
public interface Supportable {

    /**
     *
     * @param tipoDocumento Tipo de documento a soportar
     * @return Verdadero si soporta el tipo de documento, falso en caso contrario
     */
    default boolean supports(DocumentEnum tipoDocumento) {
        return false;
    }

}
package com.rolandopalermo.facturacion.ec.common.interfaces;

import com.rolandopalermo.facturacion.ec.common.types.DocumentType;

/**
 * Interfaz para definir si una clase soporta un tipo de documento específico
 *
 * @author Rolando
 */
public interface Supportable {

    /**
     * @param documentType Tipo de documento a soportar
     * @return Verdadero si soporta el tipo de documento, falso en caso contrario
     */
    default boolean supports(DocumentType documentType) {
        return false;
    }

}

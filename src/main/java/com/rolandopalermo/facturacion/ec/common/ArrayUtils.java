package com.rolandopalermo.facturacion.ec.common;

import lombok.experimental.UtilityClass;

/**
 * Utilitarios para el manejo de arrays
 *
 * @author Rolando Rodríguez
 */
@UtilityClass
public class ArrayUtils {

    /**
     * Verifica si un array de objetos es vacío o nulo
     *
     * @param array Array de objetos
     * @return Verdadero si es nulo, falso en caso contrario
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Verifica si un array de objetos no es vacío ni nulo
     *
     * @param array Array de objetos
     * @return Falso si es nulo, verdadero en caso contrario
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

}

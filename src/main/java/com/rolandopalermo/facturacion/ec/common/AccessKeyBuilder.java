package com.rolandopalermo.facturacion.ec.common;

import lombok.Builder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase para la generación de claves de acceso
 *
 * @author Rolando
 */
@Builder
public class AccessKeyBuilder {

    private final Date fechaEmision;
    private final String tipoComprobante;
    private final String ruc;
    private final String ambiente;
    private final String serie;
    private final String numeroComprobante;
    private final String tipoEmision;
    private final String codigoNumerico;

    /**
     * Método para la generación de una clave de acceso
     *
     * @return Clave de acceso de 49 dígitos
     */
    public String generarClaveAcceso() {
        int verificador = 0;
        String formattedRuc = this.ruc;
        if (formattedRuc != null && formattedRuc.length() < 13) {
            formattedRuc = String.format("%013d", new Object[]{formattedRuc});
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String fecha = dateFormat.format(fechaEmision);
        StringBuilder clave = new StringBuilder(fecha);
        clave.append(tipoComprobante);
        clave.append(formattedRuc);
        clave.append(ambiente);
        clave.append(serie);
        clave.append(numeroComprobante);
        clave.append(codigoNumerico);
        clave.append(tipoEmision);
        verificador = generarDigitoModulo11(clave.toString());
        clave.append(Integer.valueOf(verificador));
        String claveGenerada = clave.toString();
        if (clave.toString().length() != 49) {
            claveGenerada = null;
        }
        return claveGenerada;
    }

    /**
     * Generación de dígito verificador utilizando el algoritmo del Módulo 11
     *
     * @param cadena Dígito verificador
     * @return
     */
    private int generarDigitoModulo11(String cadena) {
        int baseMultiplicador = 7;
        int aux[] = new int[cadena.length()];
        int multiplicador = 2;
        int total = 0;
        int verificador = 0;
        for (int i = aux.length - 1; i >= 0; i--) {
            aux[i] = Integer.parseInt((new StringBuilder()).append(cadena.charAt(i)).toString());
            aux[i] = aux[i] * multiplicador;
            if (++multiplicador > baseMultiplicador) {
                multiplicador = 2;
            }
            total += aux[i];
        }

        if (total == 0 || total == 1) {
            verificador = 0;
        } else {
            verificador = 11 - total % 11 != 11 ? 11 - total % 11 : 0;
        }
        if (verificador == 10) {
            verificador = 1;
        }
        return verificador;
    }

}

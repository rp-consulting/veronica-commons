package com.rolandopalermo.facturacion.ec.common.types;

import com.rolandopalermo.facturacion.ec.common.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

import static com.rolandopalermo.facturacion.ec.common.Constants.MAX_ACCESS_KEY_LENGTH;

public enum DocumentEnum {

    FACTURA("01", "factura", "Factura"),
    LIQUIDACION_COMPRAS("03", "liquidacionCompra", "Liquidación de compra"),
    NOTA_CREDITO("04", "notaCredito", "Nota de crédito"),
    NOTA_DEBITO("05", "notaDebito", "Nota de débito"),
    GUITA_REMISION("06", "guiaRemision", "Guía de remisión"),
    COMPROBANTE_RETENCION("07", "comprobanteRetencion", "Comprobante de retención");

    private String code;
    private String nombre;
    private String description;

    DocumentEnum(String code, String nombre, String description) {
        this.code = code;
        this.nombre = nombre;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getNombre() {
        return nombre;
    }

    public static Optional<DocumentEnum> getFromCode(String documentType) {
        return get(documentType);
    }

    public static Optional<DocumentEnum> getFromAccessKey(String accessKey) {
        if (!StringUtils.isEmpty(accessKey) && accessKey.length() == MAX_ACCESS_KEY_LENGTH) {
            String documentType = accessKey.substring(8, 10);
            return get(documentType);
        }
        return Optional.empty();
    }

    private static Optional<DocumentEnum> get(String documentType) {
        return Stream.of(DocumentEnum.values())
                .filter(p -> p.getCode().compareTo(documentType) == 0)
                .findFirst();
    }

}
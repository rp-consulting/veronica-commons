package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.types.DocumentType;
import com.rolandopalermo.facturacion.ec.common.types.InternalStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SriEntity {

    private String accessKey;
    private InternalStatusType internalStatus;
    private String authorizationDate;
    private String authorization;
    private String receipt;
    private String supplierNumber;
    private DocumentType documentType;
    private String rootElement;
    private String establishment;

}

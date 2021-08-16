package com.rolandopalermo.facturacion.ec.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SriException extends RuntimeException {

    private Object sriResponse;

}

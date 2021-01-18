package com.rolandopalermo.facturacion.ec.common;

@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {

    R apply(T t) throws E;

}
package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum ContentType {

    XML("xml", "application/xml; charset=UTF-8"),
    JSON("json", "application/json"),
    PDF("pdf", "application/octet-stream"),
    SIGNED_XML("signed_xml", "application/xml; charset=UTF-8");

    private String extension;
    private String contentType;

    ContentType(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension() {
        return extension;
    }

    public String getContentType() {
        return contentType;
    }

    public static Optional<ContentType> get(String extension) {
        return Stream.of(ContentType.values())
                .filter(p -> p.getExtension().compareToIgnoreCase(extension) == 0)
                .findFirst();
    }

}

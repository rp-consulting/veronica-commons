package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum ContentTypeEnum {

    XML("xml", "application/xml; charset=UTF-8"),
    PDF("pdf", "application/octet-stream"),
    RECEIPT("plain", "application/xml; charset=UTF-8"),
    TEXT("base64pdf", "text/plain");

    private String extension;
    private String contentType;

    ContentTypeEnum(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getExtension() {
        return extension;
    }

    public String getContentType() {
        return contentType;
    }

    public static Optional<ContentTypeEnum> get(String extension) {
        return Stream.of(ContentTypeEnum.values())
                .filter(p -> p.getExtension().compareToIgnoreCase(extension) == 0)
                .findFirst();
    }

}
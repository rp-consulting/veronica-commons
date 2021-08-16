package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.text.MessageFormat.format;

@UtilityClass
public class FileUtils {

    public static String readContent(String resourcePath) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            try (InputStream is = classloader.getResourceAsStream(resourcePath)) {
                return IOUtils.toString(is, UTF_8);
            }
        } catch (IOException ex) {
            throw new VeronicaException(format("Ocurrió un error al leer el contenido del archivo [{0}]", resourcePath));
        }
    }

    public File createTemporalFile(byte[] content, String suffix) {
        try {
            String basePath = UUID.randomUUID().toString();
            File tmpFile = File.createTempFile(basePath, suffix);
            String filePath = tmpFile.getAbsolutePath();
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(content);
            }
            return tmpFile;
        } catch (IOException ex) {
            throw new VeronicaException("Ocurrió un error al crear el archivo temporal");
        }
    }

}

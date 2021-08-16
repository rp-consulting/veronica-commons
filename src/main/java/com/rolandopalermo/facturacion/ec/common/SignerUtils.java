package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.UUID;

import static com.rolandopalermo.facturacion.ec.common.Constants.ID_CE_CERTIFICATE_POLICIES;
import static com.rolandopalermo.facturacion.ec.common.DateUtils.fromDateToLocalDateTime;
import static java.util.Objects.nonNull;

@UtilityClass
public final class SignerUtils {

    /**
     * <p>
     * Verifica la existencia de políticas en el certificado utilizando el campo
     * <b>id-ce-certificatePolicies</b> con ID <b>2.5.29.32</b>.
     * </p>
     *
     * @param certificate certificado a examinar
     * @return true si encuentra políticas, false si no encuentra políticas o el certificado es nulo
     */
    public static boolean hasCertificatePolicies(X509Certificate certificate) {
        if (nonNull(certificate)) {
            byte[] certificatePolicies = certificate.getExtensionValue(ID_CE_CERTIFICATE_POLICIES);
            if (nonNull(certificatePolicies) && certificatePolicies.length > 0) {
                return true;
            }
        }
        return false;
    }

    public static LocalDateTime getExpirationDate(byte[] cert, char[] password) {
        try {
            LocalDateTime expirationDate = null;
            KeyStore p12 = KeyStore.getInstance("pkcs12");
            File temp = File.createTempFile(UUID.randomUUID().toString(), ".p12");
            try (FileOutputStream fos = new FileOutputStream(temp)) {
                fos.write(cert);
            }
            p12.load(new FileInputStream(temp.getAbsolutePath()), password);
            Enumeration<String> e = p12.aliases();
            while (e.hasMoreElements()) {
                String alias = e.nextElement();
                X509Certificate c = (X509Certificate) p12.getCertificate(alias);
                if (SignerUtils.hasCertificatePolicies(c)) {
                    expirationDate = fromDateToLocalDateTime(c.getNotAfter());
                    break;
                }
            }
            temp.delete();
            return expirationDate;
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException ex) {
            throw new VeronicaException("No se pudo obtener la fecha de expiración del certificado digital");
        }
    }

}

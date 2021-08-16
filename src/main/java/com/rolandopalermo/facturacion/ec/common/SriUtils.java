package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.InvalidInternalStatusException;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.common.types.DocumentType;
import com.rolandopalermo.facturacion.ec.common.types.InternalStatusType;
import lombok.experimental.UtilityClass;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathExpressionException;
import java.util.Optional;

import static com.rolandopalermo.facturacion.ec.common.Constants.ISO_OFFSET_DATE_TIME_PATTERN;
import static com.rolandopalermo.facturacion.ec.common.Constants.SRI_APPLIED;
import static com.rolandopalermo.facturacion.ec.common.Constants.SRI_INVALID;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_ACCESS_KEY;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_AUTHORIZATION_ACCESS_KEY;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_AUTHORIZATION_DATE;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_AUTHORIZATION_REQUESTED_ACCESS_KEY;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_AUTHORIZATION_STATUS;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_BRANCH;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_COD_DOC;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_SUPPLIER_NUMBER;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_WITHOUT_WRAPPER;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_WITH_WRAPPER;
import static com.rolandopalermo.facturacion.ec.common.DateUtils.determinePattern;
import static com.rolandopalermo.facturacion.ec.common.DateUtils.fromGregorianToDateTime;
import static com.rolandopalermo.facturacion.ec.common.StringUtils.getEstablishment;
import static com.rolandopalermo.facturacion.ec.common.StringUtils.getIssuerFromAccessKey;
import static com.rolandopalermo.facturacion.ec.common.XmlUtils.evalXPath;
import static com.rolandopalermo.facturacion.ec.common.XmlUtils.fromStringToDocument;
import static com.rolandopalermo.facturacion.ec.common.XmlUtils.getRootElement;
import static com.rolandopalermo.facturacion.ec.common.types.InternalStatusType.APPLIED;
import static com.rolandopalermo.facturacion.ec.common.types.InternalStatusType.CREATED;
import static com.rolandopalermo.facturacion.ec.common.types.InternalStatusType.REJECTED;

@UtilityClass
public class SriUtils {

    public static SriEntity getReceiptDetails(String xml) {
        try {
            Document document = fromStringToDocument(xml);
            Optional<String> receipt = evalXPath(document, XML_XPATH_WITHOUT_WRAPPER);
            if (receipt.isPresent()) {
                return buildReceiptDetails(document, xml, receipt);
            }
            receipt = evalXPath(document, XML_XPATH_WITH_WRAPPER);
            if (receipt.isPresent()) {
                return buildReceiptDetails(document, xml, receipt);
            }
            String accessKey = evalXPath(document, XML_XPATH_ACCESS_KEY).orElse(null);
            Optional<String> optionalSupplierNumber = evalXPath(document, XML_XPATH_SUPPLIER_NUMBER);
            Optional<String> optionalDocCod = evalXPath(document, XML_XPATH_COD_DOC);
            Optional<String> optionalEstablishment = evalXPath(document, XML_XPATH_BRANCH);
            Optional<DocumentType> optionalDocumentType = DocumentType.getFromCode(optionalDocCod.get());
            return SriEntity.builder()
                    .accessKey(accessKey)
                    .internalStatus(CREATED)
                    .supplierNumber(optionalSupplierNumber.get())
                    .receipt(xml)
                    .documentType(optionalDocumentType.get())
                    .rootElement(getRootElement(document))
                    .establishment(optionalEstablishment.get())
                    .build();
        } catch (Exception ex) {
            throw new VeronicaException("Ocurrió un error al leer la información del comprobante electrónico");
        }
    }

    public static InternalStatusType getInternalStatusType(String internalStatus) {
        switch (internalStatus) {
            case SRI_APPLIED:
                return APPLIED;
            case SRI_INVALID:
                return REJECTED;
        }
        throw new InvalidInternalStatusException(internalStatus);
    }

    private static SriEntity buildReceiptDetails(Document document, String xml, Optional<String> receipt) throws Exception {
        Optional<String> optionalAccessKey = evalXPath(document, XML_XPATH_AUTHORIZATION_ACCESS_KEY);
        if (!optionalAccessKey.isPresent()) {
            optionalAccessKey = evalXPath(document, XML_XPATH_AUTHORIZATION_REQUESTED_ACCESS_KEY);
        }
        return SriEntity.builder()
                .accessKey(optionalAccessKey.get())
                .internalStatus(getInternalStatus(document))
                .supplierNumber(getIssuerFromAccessKey(optionalAccessKey.get()))
                .authorizationDate(formatAuthorizationDate(evalXPath(document, XML_XPATH_AUTHORIZATION_DATE).get()))
                .authorization(xml)
                .receipt(receipt.get())
                .rootElement(getRootElement(fromStringToDocument(receipt.get())))
                .documentType(getDocumentType(optionalAccessKey.get()))
                .establishment(getEstablishment(optionalAccessKey.get()))
                .build();
    }

    private static InternalStatusType getInternalStatus(Document document) throws XPathExpressionException {
        Optional<String> optionalInternalStatus = evalXPath(document, XML_XPATH_AUTHORIZATION_STATUS);
        return getInternalStatusType(optionalInternalStatus.get());
    }

    private static DocumentType getDocumentType(String accessKey) {
        return DocumentType.getFromAccessKey(accessKey).get();
    }

    private static String formatAuthorizationDate(String authorizationDate) {
        String pattern = determinePattern(authorizationDate);
        switch (pattern) {
            case ISO_OFFSET_DATE_TIME_PATTERN:
                return fromGregorianToDateTime(authorizationDate);
            default:
                return authorizationDate;
        }
    }

}

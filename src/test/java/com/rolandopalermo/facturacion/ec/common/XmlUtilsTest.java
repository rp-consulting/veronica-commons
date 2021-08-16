package com.rolandopalermo.facturacion.ec.common;

import lombok.SneakyThrows;
import org.junit.Test;
import org.w3c.dom.Document;

import java.util.Optional;

import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_AUTHORIZATION_DATE;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_BRANCH;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_BRANCH_OFFICE;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_EMAILS;
import static com.rolandopalermo.facturacion.ec.common.Constants.XML_XPATH_WITHOUT_WRAPPER;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class XmlUtilsTest {

    @Test
    @SneakyThrows
    public void When_AuthorizedXmlWithoutWrapper_Expect_AuthorizationDateNotNull() {
        String authorizedInvoice = FileUtils.readContent("authorizedInvoiceNoWrapper.xml");
        Document document = XmlUtils.fromStringToDocument(authorizedInvoice);

        Optional<String> authorizationDate = XmlUtils.evalXPath(document, XML_XPATH_AUTHORIZATION_DATE);
        assertTrue(authorizationDate.isPresent());
        assertEquals("01/02/2021 07:18:48", authorizationDate.get());
    }

    @Test
    @SneakyThrows
    public void When_AuthorizedXmlWithoutWrapper_Expect_ReceiptNotNull() {
        String authorizedInvoice = FileUtils.readContent("authorizedInvoiceNoWrapper.xml");
        Document document = XmlUtils.fromStringToDocument(authorizedInvoice);

        Optional<String> receipt = XmlUtils.evalXPath(document, XML_XPATH_WITHOUT_WRAPPER);
        assertTrue(receipt.isPresent());
        assertNotNull(receipt.get());
    }

    @Test
    @SneakyThrows
    public void When_ReceiptXml_Expect_EmailsListNotNull() {
        String authorizedInvoice = FileUtils.readContent("invoice.xml");
        Document document = XmlUtils.fromStringToDocument(authorizedInvoice);

        Optional<String> emailsList = XmlUtils.evalXPath(document, format(XML_XPATH_EMAILS, "Email"));
        assertTrue(emailsList.isPresent());
        assertEquals("info@veronica.ec", emailsList.get());
    }

    @Test
    @SneakyThrows
    public void When_ReceiptXml_Expect_BranchInfoNotNull() {
        String authorizedInvoice = FileUtils.readContent("invoice.xml");
        Document document = XmlUtils.fromStringToDocument(authorizedInvoice);

        Optional<String> branch = XmlUtils.evalXPath(document, XML_XPATH_BRANCH);
        Optional<String> branchOffice = XmlUtils.evalXPath(document, XML_XPATH_BRANCH_OFFICE);
        assertTrue(branch.isPresent());
        assertEquals("001", branch.get());
        assertTrue(branchOffice.isPresent());
        assertEquals("001", branchOffice.get());
    }

    @Test
    @SneakyThrows
    public void When_Withholding_Expect_Total() {
        String authorizedInvoice = FileUtils.readContent("withholding.xml");
        Optional<String> strTotal = XmlUtils.evalXPath(XmlUtils.fromStringToDocument(authorizedInvoice), "sum(/comprobanteRetencion/impuestos/impuesto/valorRetenido)");
        assertEquals("9.14", strTotal.get());
    }

}

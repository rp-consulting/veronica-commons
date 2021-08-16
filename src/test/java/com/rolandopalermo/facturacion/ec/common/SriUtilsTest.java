package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.types.DocumentType;
import com.rolandopalermo.facturacion.ec.common.types.InternalStatusType;
import lombok.SneakyThrows;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SriUtilsTest {

    @Test
    @SneakyThrows
    public void When_AuthorizedXmlWithoutWrapper_Expect_AuthorizationInfo() {
        String authorizedInvoice = FileUtils.readContent("authorizedInvoiceNoWrapper.xml");
        SriEntity sriEntity = SriUtils.getReceiptDetails(authorizedInvoice);
        assertNotNull(sriEntity);
        assertEquals("01/02/2021 07:18:48", sriEntity.getAuthorizationDate());
        assertEquals("0102202101179210652400120010010003722251234567816", sriEntity.getAccessKey());
        assertEquals(InternalStatusType.APPLIED, sriEntity.getInternalStatus());
        assertNotNull(sriEntity.getReceipt());
    }

    @Test
    @SneakyThrows
    public void When_AuthorizedXmlWithWrapper_Expect_AuthorizationInfo() {
        String authorizedInvoice = FileUtils.readContent("authorizedInvoiceWithWrapper.xml");
        SriEntity sriEntity = SriUtils.getReceiptDetails(authorizedInvoice);
        assertNotNull(sriEntity);
        assertEquals("23/02/2021 13:41:57", sriEntity.getAuthorizationDate());
        assertEquals("2302202101050350121500110010020000000053668389114", sriEntity.getAccessKey());
        assertEquals(InternalStatusType.APPLIED, sriEntity.getInternalStatus());
        assertNotNull(sriEntity.getReceipt());
    }

    @Test
    @SneakyThrows
    public void When_ReceiptXml_Expect_AuthorizationInfo() {
        String authorizedInvoice = FileUtils.readContent("invoice.xml");
        SriEntity sriEntity = SriUtils.getReceiptDetails(authorizedInvoice);
        assertNotNull(sriEntity);
        assertNull(sriEntity.getAuthorizationDate());
        assertNull(sriEntity.getAuthorization());
        assertEquals("0102202101179210652400120010010003722251234567815", sriEntity.getAccessKey());
        assertEquals(InternalStatusType.CREATED, sriEntity.getInternalStatus());
        assertEquals(DocumentType.FACTURA, sriEntity.getDocumentType());
        assertNotNull(sriEntity.getReceipt());
    }

    @Test
    @SneakyThrows
    public void When_ReceiptXmlWithoutAccessKey_Expect_AuthorizationInfo() {
        String authorizedInvoice = FileUtils.readContent("invoice_noAccessKey.xml");
        SriEntity sriEntity = SriUtils.getReceiptDetails(authorizedInvoice);
        assertNotNull(sriEntity);
        assertNull(sriEntity.getAuthorizationDate());
        assertNull(sriEntity.getAuthorization());
        assertEquals(InternalStatusType.CREATED, sriEntity.getInternalStatus());
        assertEquals(DocumentType.FACTURA, sriEntity.getDocumentType());
        assertNotNull(sriEntity.getReceipt());
        assertNull(sriEntity.getAccessKey());
        assertNotNull(sriEntity.getSupplierNumber());
    }

    @Test
    @SneakyThrows
    public void When_NoTAuthorizedXml_Expect_AuthorizationInfo() {
        String authorizedInvoice = FileUtils.readContent("notAuthorizedInvoice.xml");
        SriEntity sriEntity = SriUtils.getReceiptDetails(authorizedInvoice);
        assertNotNull(sriEntity);
        assertEquals("29/03/2021 21:41:25", sriEntity.getAuthorizationDate());
        assertEquals("2903202101050350121500110010010000000093287184017", sriEntity.getAccessKey());
        assertEquals(InternalStatusType.REJECTED, sriEntity.getInternalStatus());
        assertNotNull(sriEntity.getReceipt());
    }

    @Test
    @SneakyThrows
    public void When_AuthorizedWithHoldingXmlWithoutWrapper_Expect_AuthorizationInfo() {
        String authorizedInvoice = FileUtils.readContent("authorizedWithHoldingNoWrapper.xml");
        SriEntity sriEntity = SriUtils.getReceiptDetails(authorizedInvoice);
        assertNotNull(sriEntity);
        assertEquals("05/01/2021 19:16:39", sriEntity.getAuthorizationDate());
        assertEquals("0501202107179264789400120011000000015831757656518", sriEntity.getAccessKey());
        assertEquals(InternalStatusType.APPLIED, sriEntity.getInternalStatus());
        assertNotNull(sriEntity.getReceipt());
    }

}

package com.rolandopalermo.facturacion.ec.common;

import lombok.SneakyThrows;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    @SneakyThrows
    public void When_ValidAccessKey_Expect_Branch() {
        String branch = StringUtils.getEstablishment("0102202101179210652400120010010003722251234567815");
        assertEquals(branch, "001");
    }

    @Test
    @SneakyThrows
    public void When_ValidSequence_Expect_FormattedSequence() {
        String formattedSequence = StringUtils.formatSequenceNumber("123");
        assertEquals(formattedSequence, "000000123");
    }

}

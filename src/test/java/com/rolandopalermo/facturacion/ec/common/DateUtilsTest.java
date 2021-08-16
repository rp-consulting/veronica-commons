package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.InvalidDateFormatException;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DateUtilsTest {

    @Test
    public void When_ValidDate_Expect_KnowFormat() throws InvalidDateFormatException {
        String date = "01/02/2021";
        DateTimeFormatter format = DateUtils.determineDateFormat(date);
        assertNotNull(format);
    }

    @Test
    public void When_ValidDateTime_Expect_KnowFormat() throws InvalidDateFormatException {
        String date = "01/02/2021 07:18:48";
        DateTimeFormatter format = DateUtils.determineDateFormat(date);
        assertNotNull(format);
    }

    @Test
    public void When_ValidMinifiedDate_Expect_KnowFormat() throws InvalidDateFormatException {
        String date = "23022021";
        DateTimeFormatter format = DateUtils.determineDateFormat(date);
        assertNotNull(format);
    }

    @Test
    public void When_ValidDateTimeWithTZ_Expect_KnowFormat() throws InvalidDateFormatException {
        String date = "2021-02-23T13:41:57-05:00";
        DateTimeFormatter format = DateUtils.determineDateFormat(date);
        assertNotNull(format);
    }

    @Test
    public void When_GregorianDate_Expect_DateTime() throws ParseException {
        String date = "2021-02-23T13:41:57-05:00";
        String format = DateUtils.fromGregorianToDateTime(date);
        assertEquals(format, "23/02/2021 13:41:57");
    }

    @Test
    public void When_MinifiedDate_Expect_LocalDateTime() throws InvalidDateFormatException {
        LocalDate ld = DateUtils.fromStringToLocalDate("23022021");
        assertNotNull(ld);
        assertEquals(ld.getDayOfMonth(), 23);
        assertEquals(ld.getMonth().getValue(), 2);
        assertEquals(ld.getYear(), 2021);
    }

    @Test
    public void When_DateTimeWithTZ_Expect_LocalDateTime() throws InvalidDateFormatException {
        LocalDateTime ldt = DateUtils.fromStringToLocalDateTime("2021-02-23T13:41:57-05:00");
        assertNotNull(ldt);
        assertEquals(ldt.getDayOfMonth(), 23);
        assertEquals(ldt.getMonth().getValue(), 2);
        assertEquals(ldt.getYear(), 2021);
        assertEquals(ldt.getHour(), 13);
        assertEquals(ldt.getMinute(), 41);
        assertEquals(ldt.getSecond(), 57);
    }

    @Test
    public void When_DateTimeWith_Expect_LocalDateTime() throws InvalidDateFormatException {
        LocalDateTime ldt = DateUtils.fromStringToLocalDateTime("01/02/2021 07:18:48");
        assertNotNull(ldt);
        assertEquals(ldt.getDayOfMonth(), 01);
        assertEquals(ldt.getMonth().getValue(), 2);
        assertEquals(ldt.getYear(), 2021);
        assertEquals(ldt.getHour(), 7);
        assertEquals(ldt.getMinute(), 18);
        assertEquals(ldt.getSecond(), 48);
    }

}

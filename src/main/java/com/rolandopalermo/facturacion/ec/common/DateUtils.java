package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.InvalidDateException;
import com.rolandopalermo.facturacion.ec.common.exception.InvalidDateFormatException;
import com.rolandopalermo.facturacion.ec.common.exception.InvalidDateRangeException;
import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_FORMAT;
import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_MINIFIED_FORMAT;
import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_TIME_FORMAT;
import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_TIME_FORMAT_PATTERN;
import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_TIME_FORMAT_WITH_TZ;
import static com.rolandopalermo.facturacion.ec.common.Constants.ISO_OFFSET_DATE_TIME_PATTERN;
import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static java.time.format.DateTimeFormatter.ofPattern;

@UtilityClass
public class DateUtils {

    private static final Map<String, DateTimeFormatter> DATE_FORMAT_REGEXPS = new HashMap<String, DateTimeFormatter>() {{
        put("^\\d{8}$", ofPattern(DATE_MINIFIED_FORMAT));
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", ofPattern(DATE_FORMAT));
        put(DATE_TIME_FORMAT_PATTERN, ofPattern(DATE_TIME_FORMAT));
        put("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}$", ofPattern(DATE_TIME_FORMAT_WITH_TZ));
        put(ISO_OFFSET_DATE_TIME_PATTERN, ISO_OFFSET_DATE_TIME);
    }};

    public static DateTimeFormatter determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        throw new InvalidDateFormatException(dateString);
    }

    public static String determinePattern(String dateString) {
        for (Map.Entry<String, DateTimeFormatter> entry : DATE_FORMAT_REGEXPS.entrySet()) {
            if (dateString.matches(entry.getKey())) {
                return entry.getKey();
            }
        }
        throw new InvalidDateFormatException(dateString);
    }

    public static LocalDateTime fromStringToLocalDateTime(String dateString) {
        DateTimeFormatter dateTimeFormatter = determineDateFormat(dateString);
        if (dateTimeFormatter == null) {
            throw new InvalidDateFormatException(dateString);
        }
        return (dateString != null) ? LocalDateTime.parse(dateString, dateTimeFormatter) : null;
    }

    public static LocalDate fromStringToLocalDate(String dateString) {
        DateTimeFormatter dateTimeFormatter = determineDateFormat(dateString);
        if (dateTimeFormatter == null) {
            throw new InvalidDateFormatException(dateString);
        }
        return (dateString != null) ? LocalDate.parse(dateString, dateTimeFormatter) : null;
    }

    public static String fromGregorianToDateTime(String inputDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_WITH_TZ);
            Date date = sdf.parse(inputDate);
            DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            return dateFormat.format(date);
        } catch (ParseException e) {
            throw new InvalidDateException(inputDate);
        }
    }

    public static LocalDate[] buildDateRange(Optional<String> startDate, Optional<String> endDate) {
        LocalDate localDate = LocalDate.now();
        LocalDate start = null;
        LocalDate end = null;
        if (startDate.isPresent() && !endDate.isPresent()) {
            start = fromStringToLocalDate(startDate.get());
            end = localDate.now().plusDays(1);
            if (start.isAfter(LocalDate.now())) {
                throw new InvalidDateRangeException(start, end);
            }
        }
        if (!startDate.isPresent() && endDate.isPresent()) {
            end = fromStringToLocalDate(endDate.get()).plusDays(1);
            if (end.isAfter(LocalDate.now().plusDays(1))) {
                throw new InvalidDateRangeException(start, end);
            }
        }
        if (startDate.isPresent() && endDate.isPresent()) {
            start = fromStringToLocalDate(startDate.get());
            end = fromStringToLocalDate(endDate.get()).plusDays(1);
            if (end.isBefore(start) || end.isEqual(start)) {
                throw new InvalidDateRangeException(start, end);
            }
        }
        return new LocalDate[]{start, end};
    }

    public static LocalDateTime fromDateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static String fromLocalDateTimeToString(LocalDateTime date) {
        return (date != null) ? date.format(ofPattern(DATE_TIME_FORMAT)) : "";
    }

    public static String fromLocalDateToString(LocalDate date) {
        return (date != null) ? date.format(ofPattern(DATE_FORMAT)) : "";
    }

    public static String fromLocalDateTimeToString(LocalDateTime date, String format) {
        return (date != null) ? date.format(ofPattern(format)) : "";
    }

    public static String fromLocalDateToString(LocalDate date, String format) {
        return (date != null) ? date.format(ofPattern(format)) : "";
    }

}

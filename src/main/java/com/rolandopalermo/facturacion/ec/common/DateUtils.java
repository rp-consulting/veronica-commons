package com.rolandopalermo.facturacion.ec.common;

import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_FORMAT;
import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_FORMAT_TZ;
import static com.rolandopalermo.facturacion.ec.common.Constants.DATE_TIME_FORMAT;

/**
 * Utilitarios para el manejo de fechas
 *
 * @author Rolando Rodríguez
 */
public class DateUtils {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private DateUtils() {
    }

    /**
     * Convierte una fecha de formato yyyy-MM-dd'T'HH:mm:ss a formato dd/MM/yyyy HH:mm:ss
     *
     * @param inputDate Fecha en formato yyyy-MM-dd'T'HH:mm:ss
     * @return Fecha en formato dd/MM/yyyy HH:mm:ss
     */
    public static String convertirGreggorianToDDMMYYYY(String inputDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TZ);
            Date date = sdf.parse(inputDate);
            DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            return dateFormat.format(date);
        } catch (ParseException e) {
            throw new VeronicaException(String.format("Ocurrió un error al formatear la fecha [%s]", inputDate));
        }
    }

    /**
     * Convierte un objeto fecha a una cadena de texto dd/MM/yyyy HH:mm:ss
     *
     * @param timestamp Objeto fecha
     * @return Fecha en formato dd/MM/yyyy HH:mm:ss
     */
    public static String convertirTimestampToDate(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        Date date = new Date(timestamp.getTime());
        DateFormat formattedDate = new SimpleDateFormat(DATE_TIME_FORMAT);
        return formattedDate.format(date);
    }

    /**
     * Convierte un objeto fecha a una cadena de texto dd/MM/yyyy
     *
     * @param fecha Objeto fecha
     * @return Fecha en formato dd/MM/yyyy
     */
    public static String getFechaFromDate(Date fecha) {
        if (fecha == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(fecha);
    }

    /**
     * Convierte una cadena de texto en formato dd/MM/yyyy a un objeto fecha
     *
     * @param inputDate Cadena de texto en formato dd/MM/yyyy
     * @return Objeto fecha
     */
    public static Date getFechaFromStringddMMyyyy(String inputDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.parse(inputDate);
        } catch (ParseException e) {
            throw new VeronicaException(String.format("Ocurrió un error al formatear la fecha [%s]", inputDate));
        }
    }

    public static String getFechaFromLocalDateTime(LocalDateTime inputDate) {
        return (inputDate != null) ? inputDate.format(DATE_FORMATTER) : "";
    }

    public static String getFechaHoraFromLocalDateTime(LocalDateTime inputDate) {
        return (inputDate != null) ? inputDate.format(DATE_TIME_FORMATTER) : "";
    }

    public static LocalDate getFechaFromString(String inputDate) {
        return (!StringUtils.isEmpty(inputDate)) ? LocalDate.parse(inputDate, DATE_FORMATTER) : null;
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
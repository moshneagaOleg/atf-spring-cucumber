package io.tpd.springbootcucumber.core.util;

import java.security.SecureRandom;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public abstract class DateUtils {

    public static String getCurrentDate() {
        return getCurrentDate("dd-MM-yyyy");
    }

    /**
     * @param datePattern might include time (ex.: "dd-MM-yyyy", "MMM dd yyyy HH:mm:ss")
     * @return time as String
     */
    public static String getCurrentDate(String datePattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return formatter.format(LocalDate.now());
    }

    /**
     * @param datePattern might include time (ex.: "dd-MM-yyyy", "MMM dd yyyy HH:mm:ss")
     * @return time as String
     */
    public static String getDateTime(LocalDateTime localDateTime, String datePattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return formatter.format(localDateTime);
    }

    public static LocalDate getLocalDate(String date, String datePattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime getLocalDateTime(String date, String dateTimePattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Convert month value in short month string
     *
     * @param month ex: 10
     * @return String ex: sep, oct
     */
    public static String getShortMonth(int month) {
        return new DateFormatSymbols().getShortMonths()[month - 1];
    }

    /**
     * Convert string month to month value
     *
     * @param monthName ex: September
     * @return Integer ex: 12
     */
    public static Integer getMonthValue(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }

    /**
     * @param min (inclusive)
     * @param max (exclusive)
     * @return random int value
     */
    public static int generateRandomInt(int min, int max) {
        try {
            return new SecureRandom().ints(min, max).findFirst().getAsInt();
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

}

package utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeConverter {

    private static final String[] RU_MONTHS_GENITIVE = {
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    };

    private static final String[] RU_MONTHS = {
            "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
            "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    };

    /**
     * Generates human readable string from a unix timestamp with Russian names of months.
     * @param date unix timestamp.
     * @return human readable string.
     */
    public static String fromLong(final Long date) {

        if (date == null) {
            return "";
        } else {
            DateTime dateTime = new DateTime(date * 1000L, DateTimeZone.UTC);
            StringBuffer result = new StringBuffer();

            result.append(dateTime.dayOfMonth().getAsShortText());
            result.append(" ");
            result.append(RU_MONTHS_GENITIVE[dateTime.getMonthOfYear() - 1]);
            result.append(" ");
            result.append(dateTime.year().getAsText());

            return result.toString();
        }
    }

    /**
     * Generates human readable string from a unix timestamp with Russian names of months
     * with time (hours and minutes).
     * @param date unix timestamp.
     * @return human readable string.
     */
    public static String fromLongWithTime(final Long date) {

        if (date == null) {
            return "";
        } else {
            DateTime dateTime = new DateTime(date * 1000L, DateTimeZone.UTC);
            StringBuffer result = new StringBuffer();

            result.append(dateTime.dayOfMonth().getAsShortText());
            result.append(" ");
            result.append(RU_MONTHS_GENITIVE[dateTime.getMonthOfYear() - 1]);
            result.append(" ");
            result.append(dateTime.year().getAsText());
            result.append(" ");
            DateTimeFormatter timeFormat = DateTimeFormat.forPattern("kk:mm");
            result.append(timeFormat.print(dateTime));

            return result.toString();
        }
    }


    /**
     * Generates human readable string from DateTime "month year" localized
     * @param dateTime  Joda DateTime date.
     * @return human readable string.
     */
    public static String fromDateTimeToMonthYear(final DateTime dateTime) {

        if (dateTime == null) {
            return "";
        } else {
            StringBuffer result = new StringBuffer();

            result.append(RU_MONTHS[ dateTime.getMonthOfYear() - 1 ]);
            result.append(" ");
            result.append(dateTime.year().getAsText());

            return result.toString();
        }
    }
}

package utils;

import org.joda.time.DateTime;

public class DateTimeConverter {

    private static final String[] RU_MONTHS = {
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
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
            DateTime dateTime = new DateTime(date * 1000L);
            StringBuffer result = new StringBuffer();

            result.append(dateTime.dayOfMonth().getAsShortText());
            result.append(" ");
            result.append(RU_MONTHS[dateTime.getMonthOfYear() - 1]);
            result.append(" ");
            result.append(dateTime.year().getAsText());
//            result.append(" ");
//            result.append("года");

            return result.toString();
        }
    }
}

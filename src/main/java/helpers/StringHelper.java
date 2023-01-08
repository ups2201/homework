package helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringHelper {

    public static LocalDate getDateFromString(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withLocale(new Locale("ru"));
        return LocalDate.parse(dateString, formatter);
    }
}

package ru.job4j.ood.srp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timer {
    private final LocalDateTime localDateTime = LocalDateTime.now();

    /* Преобразование впоследствии может измениться. Нарушение SRP.*/
    public String dateFormat() {
        return localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}

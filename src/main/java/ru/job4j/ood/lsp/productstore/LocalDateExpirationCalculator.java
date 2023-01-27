package ru.job4j.ood.lsp.productstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LocalDateExpirationCalculator implements ExpirationCalculator<LocalDate> {

    @Override
    public double calculateInPercent(LocalDate startDate, LocalDate endDate) {
        var expiryPeriod = ChronoUnit.DAYS.between(startDate, endDate);
        var daysPassed = ChronoUnit.DAYS.between(startDate, LocalDate.now());
        return (double) daysPassed / expiryPeriod * 100;
    }
}

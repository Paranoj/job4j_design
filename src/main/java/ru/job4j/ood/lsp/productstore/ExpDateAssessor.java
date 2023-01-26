package ru.job4j.ood.lsp.productstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ExpDateAssessor {

    public double expDateAssessor(LocalDate createDate, LocalDate expiryDate) {
        var expiryPeriod = ChronoUnit.DAYS.between(createDate, expiryDate);
        var daysPassed = ChronoUnit.DAYS.between(createDate, LocalDate.now());
        return (double) daysPassed / expiryPeriod;
    }
}

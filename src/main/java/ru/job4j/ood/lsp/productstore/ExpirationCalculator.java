package ru.job4j.ood.lsp.productstore;

public interface ExpirationCalculator<T> {
    double calculateInPercent(T startDate, T endDate);
}

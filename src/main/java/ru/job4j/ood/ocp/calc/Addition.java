package ru.job4j.ood.ocp.calc;

public class Addition implements CalculatorOperation {
    private double left;
    private double right;
    private double rsl;

    public Addition(double left, double right, double rsl) {
        this.left = left;
        this.right = right;
        this.rsl = rsl;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getRsl() {
        return rsl;
    }

    public void setRsl(double rsl) {
        this.rsl = rsl;
    }
}

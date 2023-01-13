package ru.job4j.ood.ocp.calc;

import java.security.InvalidParameterException;

public class Calculator {

    /* Нарушение OCP.
    Несмотря на то, что был выделен интерфейс CalculatorOperation, он не содержит методов абстракции.
    Поэтому, для дальнейшего расширения программы новыми операциями потребуется вносить изменения в метод calculate.
    Интерфейс должен содержать метод operation, который будет использоваться в его реализациях. Это инкапсулирует логику
    операций калькулятора и улучшит читаемость кода.
    */
    public void calculate(CalculatorOperation operation) {
        if (operation == null) {
            throw new InvalidParameterException("Can not perform operation");
        }

        if (operation instanceof Addition addition) {
            addition.setRsl(addition.getLeft() + addition.getRight());
        } else if (operation instanceof Subtraction subtraction) {
            subtraction.setRsl(subtraction.getLeft() - subtraction.getRight());
        }
    }
}


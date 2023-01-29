package ru.job4j.ood.dip.example;

public class PersonalComputer {

    /* Нарушение DIP. Объявив StandardKeyboard и Monitor с помощью ключевого слова new, мы тесно связали
     эти три класса вместе. Конструктор зависит от конкретных реализаций, а не от абстракций.*/
    private final StandardKeyboard keyboard;
    private final Monitor monitor;

    public PersonalComputer() {
        monitor = new Monitor();
        keyboard = new StandardKeyboard();
    }

}

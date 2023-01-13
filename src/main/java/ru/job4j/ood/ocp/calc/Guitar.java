package ru.job4j.ood.ocp.calc;

public class Guitar {

    /* Изначально модель класса Guitar имеет 3 поля - make, model, volume.
    * Впоследствии, появилась необходимость в добавлении типа гитары - электрогитара.
    * Если производить добавление поля электрогитара в этом классе, то придется изменять конструктор и
    * искать участки когда, где может быть нарушена логика работы класса. Правильным решением будет создать
    * отдельный класс ElectricGuitar, который будет являться наследником Guitar и там реализовать это поле.*/
    private String make;
    private String model;
    private int volume;

    /* Добавляемое поле*/
    private String type;

    public Guitar(String make, String model, int volume) {
        this.make = make;
        this.model = model;
        this.volume = volume;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getVolume() {
        return volume;
    }

    public void sound(Guitar guitar) {
        System.out.printf("%s makes sound with volume: %s%n", guitar.getModel(), guitar.getVolume());
    }
}

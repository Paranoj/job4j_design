package ru.job4j.ood.isp.example;

public interface AnimalOwner {
    void washTheAnimal();

    void feedTheAnimal();

    void hugTheAnimal();

    /* Нарушение ICP. Многих хищников, к примеру, нельзя обнять, т.к. это может быть опасно для здоровья
     владельца животного.*/
}

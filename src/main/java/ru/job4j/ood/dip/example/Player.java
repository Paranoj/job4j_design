package ru.job4j.ood.dip.example;

public class Player {
    private final Storage storage;

    public Player(Storage storage) {
        this.storage = storage;
    }

    /* Нарушение DIP. Проигрыватель зависит от конкретной реализации хранилища, а не от абстракции.*/
    public void play(Track track) {
        Player player = new Player(storage);
        player.play(track);
    }
}

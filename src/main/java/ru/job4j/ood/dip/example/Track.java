package ru.job4j.ood.dip.example;

public class Track {

    private final String name;
    private final String album;
    private final String genre;
    private final double length;

    public Track(String name, String album, String genre, double length) {
        this.name = name;
        this.album = album;
        this.genre = genre;
        this.length = length;
    }
}

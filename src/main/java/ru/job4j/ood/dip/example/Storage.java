package ru.job4j.ood.dip.example;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final List<Track> trackList = new ArrayList<>();

    public boolean add(Track track) {
        return trackList.add(track);
    }
}

package ru.job4j.serialization.json;

public class Model {
    private final String mod;

    public Model(String mod) {
        this.mod = mod;
    }

    public String getMod() {
        return mod;
    }

    @Override
    public String toString() {
        return "Model{"
                + "mod='" + mod + '\''
                + '}';
    }
}

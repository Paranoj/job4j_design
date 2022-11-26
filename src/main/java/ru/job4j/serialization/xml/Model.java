package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "model")
public class Model {

    @XmlAttribute
    private String mod;

    public Model() { }

    public Model(String mod) {
        this.mod = mod;
    }

    @Override
    public String toString() {
        return "Model{"
                + "mod='" + mod + '\''
                + '}';
    }
}

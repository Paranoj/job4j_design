package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    public void whenNormalFormForGeneratorIsFollowed() {
        Generator generator = new GeneratorRealization();
        Map<String, String> args = new HashMap();
        args.put("name", "Egor Bekhterev");
        args.put("subject", "you");
        assertThat("I am Egor Bekhterev, Who are you?")
                .isEqualTo(generator.produce("I am ${name}, Who are ${subject}?", args));
    }

    @Test
    public void whenInvalidKeyInTemplate() {
        Generator generator = new GeneratorRealization();
        Map<String, String> args = new HashMap();
        args.put("name", "Egor Bekhterev");
        args.put("subject", "you");
        assertThatThrownBy(() -> generator.produce("I am ${name}, Who are ${object}?", args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNeedlessKeyInMap() {
        Generator generator = new GeneratorRealization();
        Map<String, String> args = new HashMap();
        args.put("name", "Egor Bekhterev");
        args.put("subject", "you");
        args.put("needless", "key");
        assertThatThrownBy(() -> generator.produce("I am ${name}, Who are ${subject}?", args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

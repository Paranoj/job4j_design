package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Egor Bekhterev");
    }

    @Test
    void whenBlankAndHash() {
        String path = "./data/pairs_with_blank_and_hash.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenOnlyValue() throws IllegalArgumentException {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOnlyKey() throws IllegalArgumentException {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoKeyAndValueButWithEquality() throws IllegalArgumentException {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOverloadedWithEqualitySigns() {
        String path = "./data/pair_with_more_than_one_equality_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.PostgreSQLDialect=1");
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio=");
    }
}

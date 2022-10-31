package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .contains("three", "four")
                .allSatisfy(e -> {
                    assertThat(e).isGreaterThan("a");
                    assertThat(e).isLessThan("z");
                });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "2", "2", "3", "4", "4", "5");
        Set<Integer> integerSet = set.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        assertThat(integerSet).filteredOn(e -> e > 1).last().isEqualTo(5);
        assertThat(integerSet).filteredOnAssertions(e -> assertThat(e).isGreaterThan(2))
                .hasSize(3)
                .element(2).isEqualTo(5);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "three", "five");
        assertThat(map).hasSize(3)
                .containsKeys("one")
                .containsValues(0, 1, 2)
                .containsEntry("three", 1);
    }
}

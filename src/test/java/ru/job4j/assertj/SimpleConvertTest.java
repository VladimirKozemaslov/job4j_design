package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
        List<String> list = simpleConvert.toList("one", "two", "two", "three", "four", "five");
        assertThat(list).hasSize(6)
                .contains("one", "five")
                .containsOnly("one", "two", "three", "four", "five")
                .containsOnlyOnce("four", "five")
                .containsSequence("one", "two", "two")
                .containsExactly("one", "two", "two", "three", "four", "five")
                .containsExactlyInAnyOrder("two", "one", "two", "three", "four", "five")
                .containsAnyOf("six", "seven", "four")
                .doesNotContain("six", "seven")
                .startsWith("one").endsWith("five").allSatisfy(str -> {
                    assertThat(str.length()).isLessThan(6);
                    assertThat(str.length()).isGreaterThan(2);
                })
                .allMatch(str -> str.length() > 2)
                .noneMatch(str -> str.startsWith("si"));
        assertThat(list).first().isEqualTo("one");
        assertThat(list).element(2).isEqualTo("two");
        assertThat(list).last().isNotNull().isEqualTo("five");
        assertThat(list).filteredOn(str -> str.startsWith("t"))
                .hasSize(3)
                .first().isEqualTo("two");
        assertThat(list).filteredOnAssertions(
                str -> assertThat(str.length()).isLessThan(4))
                .hasSize(3)
                .element(1).isEqualTo("two");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "two", "two", "three", "four", "five");
        assertThat(set).hasSize(5)
                .contains("one", "five")
                .containsOnly("one", "two", "three", "four", "five")
                .containsOnlyOnce("four", "five")
                .containsAnyOf("six", "seven", "four")
                .doesNotContain("six", "seven")
                .allSatisfy(str -> {
                    assertThat(str.length()).isLessThan(6);
                    assertThat(str.length()).isGreaterThan(2);
                })
                .allMatch(str -> str.length() > 2)
                .noneMatch(str -> str.startsWith("si"));
        assertThat(set).filteredOn(str -> str.startsWith("t"))
                .hasSize(2);
        assertThat(set).filteredOnAssertions(
                        str -> assertThat(str.length()).isLessThan(4))
                .hasSize(2);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "two", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("three", "four", "five")
                .containsValues(0, 1, 3)
                .doesNotContainKey("six")
                .doesNotContainValue(21)
                .containsEntry("one", 0);
    }
}
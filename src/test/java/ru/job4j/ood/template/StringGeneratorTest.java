package ru.job4j.ood.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.template.Generator;
import ru.job4j.ood.tdd.template.StringGenerator;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
public class StringGeneratorTest {
    @Test
    public void whenResultMatchesHelloBorisMyNameIsVladimir() {
        Generator generator = new StringGenerator();
        String goal = "Hello Boris, my name is Vladimir.";
        String template = "Hello ${name}, my name is ${subject}";
        Map<String, String> args = Map.of("name", "Boris", "subject", "Vladimir");
        assertThat(generator.produce(template, args)).isEqualTo(goal);
    }

    @Test
    public void whenKeyFromTemplateDoesNotExistInArgs() {
        Generator generator = new StringGenerator();
        String template = "Hello ${name}, my name is ${subject}";
        Map<String, String> args = Map.of("name", "Boris");
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenKeyFromArgsDoesNotExistInTemplate() {
        Generator generator = new StringGenerator();
        String template = "Hello ${name}, my name is ${subject}";
        Map<String, String> args = Map.of("name", "Boris", "surname", "Johnson", "subject", "Vladimir");
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }
}
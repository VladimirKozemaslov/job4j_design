package ru.job4j.ood.template;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringGeneratorTest extends TestCase {
    @Test
    public void whenResultMatchesHelloBorisMyNameIsVladimir() {
        StringGenerator generator = new StringGenerator();
        String goal = "Hello Boris, my name is Vladimir.";
        String template = "Hello ${name}, my name is ${subject}";
        Map<String, String> args = Map.of("name", "Boris", "subject", "Vladimir");
        assertThat(goal).isEqualTo(generator.produce(template, args));
    }

    @Test
    public void whenKeyFromTemplateDoesNotExistInArgs() {
        StringGenerator generator = new StringGenerator();
        String template = "Hello ${name}, my name is ${subject}";
        Map<String, String> args = Map.of("name", "Boris");
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenKeyFromArgsDoesNotExistInTemplate() {
        StringGenerator generator = new StringGenerator();
        String template = "Hello ${name}, my name is ${subject}";
        Map<String, String> args = Map.of("name", "Boris", "surname", "Johnson", "subject", "Vladimir");
        assertThatThrownBy(() -> generator.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }
}
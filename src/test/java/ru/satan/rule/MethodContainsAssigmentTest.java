package ru.satan.rule;

import java.nio.file.Path;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.satan.extensions.InvalidClass;
import ru.satan.extensions.ValidClass;

final class MethodContainsAssigmentTest {

    @Test
    @ExtendWith(InvalidClass.class)
    void failsWhenInvalid(final Path clazz) {
        MatcherAssert.assertThat(
            new CompositePathRule(clazz).complaints(),
            Matchers.not(Matchers.empty())
        );
    }

    @Test
    @ExtendWith(ValidClass.class)
    void passesWhenValid(final Path clazz) {
        MatcherAssert.assertThat(
            new CompositePathRule(clazz).complaints(),
            Matchers.empty()
        );
    }
}

package ru.l3r8y.www.rule;

import java.nio.file.Path;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.www.extensions.CaseWithoutThis;
import ru.l3r8y.www.extensions.InvalidClass;
import ru.l3r8y.www.extensions.ValidClass;

final class MethodContainsAssigmentTest {

    @Test
    @ExtendWith(InvalidClass.class)
    void failsWhenInvalid(final Path clazz) {
        MatcherAssert.assertThat(
            new CompositePathRule(clazz).complaints().size(),
            Matchers.equalTo(1)
        );
    }

    /**
     * @todo #1 Write implementation to pass this test case
     * Assigment must fails not only with 'this.' construction.
     */
    @Test
    @ExtendWith(CaseWithoutThis.class)
    @Disabled
    void failsWithoutThisKeyword(final Path clazz) {
        MatcherAssert.assertThat(
            new CompositePathRule(clazz).complaints().size(),
            Matchers.equalTo(1)
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

package ru.satan.parser;

import java.nio.file.Path;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.satan.extensions.InvalidClass;

final class ClassMethodsTest {

    @Test
    @ExtendWith(InvalidClass.class)
    void parsesRightWay(final Path clazz) {
        MatcherAssert.assertThat(
            "Class contains 1 method",
            new ClassMethods(clazz).all().size(),
            Matchers.equalTo(1)
        );
    }
}
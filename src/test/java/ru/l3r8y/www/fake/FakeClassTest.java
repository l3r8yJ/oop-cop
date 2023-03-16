package ru.l3r8y.www.fake;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class FakeClassTest {

    @Test
    void worksCorrectly() {
        MatcherAssert.assertThat(
            "Invalid class exist",
            new FakeClass("ValidClass.java").asPath().toFile().exists(),
            Matchers.equalTo(true)
        );
    }

}
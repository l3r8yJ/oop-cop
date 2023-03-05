package ru.satan.rule;

import java.nio.file.Files;
import java.nio.file.Path;
import org.cactoos.bytes.BytesOf;
import org.cactoos.io.ResourceOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MethodContainsAssigmentTest {

    @Test
    void failsWhenInvalid(@TempDir final Path temp) throws Exception {
        Files.write(
            temp.resolve("Test.java"),
            new BytesOf(
                new ResourceOf("InvalidClass.java")
            ).asBytes()
        );
        MatcherAssert.assertThat(
            new CompositePathRule(temp).complaints(),
            Matchers.not(Matchers.empty())
        );
    }

    @Test
    void notFailsWhenValid(@TempDir final Path temp) throws Exception {
        Files.write(
            temp.resolve("Test.java"),
            new BytesOf(
                new ResourceOf("ValidClass.java")
            ).asBytes()
        );
        MatcherAssert.assertThat(
            new CompositePathRule(temp).complaints(),
            Matchers.empty()
        );
    }
}
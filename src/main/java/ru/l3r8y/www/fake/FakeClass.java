package ru.l3r8y.www.fake;

import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AllArgsConstructor;
import org.cactoos.bytes.BytesOf;
import org.cactoos.io.ResourceOf;

@AllArgsConstructor
public final class FakeClass {

    private final String name;

    public Path asPath() {
        try {
            final Path temp = Files.createTempDirectory(".");
            return Files.write(
                temp.resolve("Test.java"),
                new BytesOf(new ResourceOf(this.name)).asBytes()
            );
        } catch (final Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}

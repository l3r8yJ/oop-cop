package ru.l3r8y.www.parser;

import java.nio.file.Path;
import lombok.AllArgsConstructor;
import ru.l3r8y.www.Method;

@AllArgsConstructor
public final class ParsedMethod implements Method {

    private final String owner;
    private final String name;
    private final String body;
    private final Path path;

    @Override
    public String className() {
        return this.owner;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String body() {
        return this.body;
    }

    @Override
    public Path path() {
        return this.path;
    }
}

package ru.satan.parser;

import java.nio.file.Path;
import ru.satan.Method;

public final class ParsedMethod implements Method {

    private final String className;
    private final String name;
    private final String body;
    private final Path path;

    public ParsedMethod(
        final String className,
        final String name,
        final String body,
        final Path path
    ) {
        this.className = className;
        this.name = name;
        this.body = body;
        this.path = path;
    }

    @Override
    public String className() {
        return this.className;
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

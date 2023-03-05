package ru.satan;

import java.nio.file.Path;

public interface Method {

    String className();

    String name();

    String body();

    Path path();

}

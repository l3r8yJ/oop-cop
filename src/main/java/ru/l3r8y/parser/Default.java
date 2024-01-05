package ru.l3r8y.parser;

import java.nio.file.Path;
import java.util.Collection;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import ru.l3r8y.ClassName;

/**
 * Default code.
 *
 * @since 0.3.6
 */
public final class Default implements Code {

    /**
     * Names.
     */
    private final Collection<ClassName> accum;

    /**
     * Path.
     */
    private final Path path;

    /**
     * Ctor.
     *
     * @param names names
     * @param pth path
     */
    public Default(final Collection<ClassName> names, final Path pth) {
        this.accum = names;
        this.path = pth;
    }

    @Override
    public void add(final ClassOrInterfaceDeclaration declaration) {
        this.accum.add(
            new ParsedClassName(declaration.getNameAsString(), this.path)
        );
    }
}

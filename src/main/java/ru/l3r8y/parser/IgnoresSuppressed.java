package ru.l3r8y.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.List;

/**
 * Code that ignores suppressed classes.
 *
 * @since 0.3.6
 */
public final class IgnoresSuppressed implements Code {

    /**
     * Origin.
     */
    private final Code origin;
    private final List<String> all;

    /**
     * Ctor.
     *
     * @param code origin
     */
    public IgnoresSuppressed(
        final Code code,
        final List<String> chks
    ) {
        this.origin = code;
        this.all = chks;
    }

    @Override
    public void add(final ClassOrInterfaceDeclaration declaration) {
        final List<String> suppressed = new SuppressedChecks(declaration).value();
        if (suppressed.isEmpty()) {
            this.origin.add(declaration);
        }
        if (
            !new IsSuppressed(
                suppressed,
                this.all
            ).value()
        ) {
            this.origin.add(declaration);
        }
    }
}

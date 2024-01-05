package ru.l3r8y.parser;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * Class declaration.
 *
 * @since 0.3.6
 */
public final class Declaration implements CodeClass {

    /**
     * Origin.
     */
    private final Code code;

    /**
     * Class.
     */
    private final Node clazz;

    /**
     * Ctor.
     *
     * @param cd code
     * @param clzz class
     */
    public Declaration(final Code cd, final Node clzz) {
        this.code = cd;
        this.clazz = clzz;
    }

    @Override
    public void declare() {
        if (this.clazz instanceof ClassOrInterfaceDeclaration) {
            this.code.add((ClassOrInterfaceDeclaration) this.clazz);
        }
    }
}

package ru.l3r8y.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * Code to analyze.
 *
 * @since 0.3.6
 */
public interface Code {

    /**
     * Add class to analyze
     *
     * @param declaration Java class declaration to add
     */
    void add(ClassOrInterfaceDeclaration declaration);
}

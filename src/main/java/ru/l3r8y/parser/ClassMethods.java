/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023. Ivanchuck Ivan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ru.l3r8y.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import ru.l3r8y.Method;
import ru.l3r8y.Methods;

/**
 * The methods of class.
 *
 * @since 0.1.0
 */
@AllArgsConstructor
public final class ClassMethods implements Methods {

    /**
     * Path to the class.
     */
    private final Path path;

    @Override
    public Collection<Method> all() {
        final Collection<Method> methods = new ArrayList<>(0);
        try {
            final CompilationUnit parsed = StaticJavaParser.parse(this.path);
            for (final Node clazz : parsed.getChildNodes()) {
                if (clazz instanceof ClassOrInterfaceDeclaration) {
                    this.fromNodeToParsedMethod(
                        methods,
                        (ClassOrInterfaceDeclaration) clazz
                    );
                }
            }
        } catch (final IOException ex) {
            throw new IllegalStateException(
                String.format("Unable to get all methods: %s", ex.getMessage()),
                ex
            );
        }
        return methods;
    }

    /**
     * Maps methods from {@link ClassOrInterfaceDeclaration} to {@link ParsedMethod}.
     *
     * @param methods The list of methods to map
     * @param clazz The class
     */
    private void fromNodeToParsedMethod(
        final Collection<Method> methods,
        final ClassOrInterfaceDeclaration clazz
    ) {
        for (final MethodDeclaration method : clazz.getMethods()) {
            methods.add(
                new ParsedMethod(
                    clazz.getNameAsString(),
                    method.getNameAsString(),
                    method.getBody().orElse(new BlockStmt()).toString(),
                    this.path
                )
            );
        }
    }
}

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023-2024 Ivanchuck Ivan.
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
package ru.l3r8y.extensions;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import java.util.Objects;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * JUnit extension with Parser Java class declaration.
 * @since 0.3.6
 */
public final class ParserDeclaration implements ParameterResolver {

    @Override
    public boolean supportsParameter(
        final ParameterContext pctx,
        final ExtensionContext ectx
    ) {
        return Objects.equals(
            pctx.getParameter().getType(), ClassOrInterfaceDeclaration.class
        );
    }

    @Override
    public Object resolveParameter(
        final ParameterContext pctx,
        final ExtensionContext ectx
    ) {
        final ClassOrInterfaceDeclaration declaration =
            new ClassOrInterfaceDeclaration();
        declaration.setName(new SimpleName("Parser"));
        declaration.addAnnotation(
            new SingleMemberAnnotationExpr(
                new Name("SuppressWarnings"),
                new StringLiteralExpr("OOP.ErSuffixCheck")
            )
        );
        return declaration;
    }
}

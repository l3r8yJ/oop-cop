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

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;

/**
 * Is check suppressed?
 *
 * @since 0.2.6
 * @todo #38 This class is too complex.
 *  We should decompose it on many small, cohesive objects.
 *  What's missing: an object for singe/many suppression check,
 *  an object for aggregating all suppressions on class.
 *  Don't forget to remove this puzzle.
 * @todo #38 This class lacks off unit testing.
 *  ClassOrInterfaceDeclaration can't be provided in tests,
 *  so we need to do something with it in order to create small
 *  unit tests for this and other classes (see puzzle above).
 *  Don't forget to remove this puzzle.
 */
public final class IsSuppressed implements Scalar<Boolean> {

    /**
     * Suppression prefix.
     */
    private static final String PREFIX = "OOP";

    /**
     * Declaration.
     */
    private final ClassOrInterfaceDeclaration declaration;

    /**
     * Check to suppress.
     */
    private final String check;

    /**
     * Ctor.
     *
     * @param dclrtn Declaration
     * @param chk Check to suppress
     */
    public IsSuppressed(
        final ClassOrInterfaceDeclaration dclrtn,
        final String chk
    ) {
        this.declaration = dclrtn;
        this.check = chk;
    }

    // @checkstyle ReturnCountCheck (40 lines).
    @Override
    @SuppressWarnings({"PMD.OnlyOneReturn", "PMD.AvoidDeeplyNestedIfStmts"})
    public Boolean value() {
        final Optional<AnnotationExpr> annotation =
            this.declaration.getAnnotationByName("SuppressWarnings");
        if (annotation.isPresent()) {
            final List<String> suppressions = annotation.map(
                (Function<AnnotationExpr, List<String>>) expr -> {
                    final Optional<SingleMemberAnnotationExpr> single =
                        expr.toSingleMemberAnnotationExpr();
                    if (single.isPresent()) {
                        final Expression members =
                            single.get().getMemberValue();
                        // @checkstyle NestedIfDepthCheck (6 lines).
                        if (members.isStringLiteralExpr()) {
                            return new ListOf<>(
                                members.asStringLiteralExpr()
                                    .asString()
                            );
                        }
                        // @checkstyle NestedIfDepthCheck (6 lines).
                        if (members.isArrayInitializerExpr()) {
                            return members.asArrayInitializerExpr()
                                .getValues()
                                .stream()
                                .map(
                                    expression ->
                                        expression.asStringLiteralExpr()
                                            .asString()
                                ).collect(Collectors.toList());
                        }
                    }
                    return new ListOf<>();
                }
            ).get();
            return suppressions.contains(
                String.format(
                    "%s.%s",
                    IsSuppressed.PREFIX,
                    this.check
                )
            );
        }
        return false;
    }
}

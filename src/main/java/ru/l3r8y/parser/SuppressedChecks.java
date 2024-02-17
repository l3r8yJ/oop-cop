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
package ru.l3r8y.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;

/*
 * @todo #38 This class is too complex.
 *  We should decompose it on many small, cohesive objects.
 *  What's missing: an object for singe/many suppression check,
 *  this object will just aggregate all suppressions.
 *  Don't forget to remove this puzzle.
 *
 * */
/**
 * Suppressed checks.
 *
 * @since 0.2.6
 */
public final class SuppressedChecks implements Scalar<List<String>> {

    /**
     * Declaration.
     */
    private final ClassOrInterfaceDeclaration declaration;

    /**
     * Ctor.
     *
     * @param dclrtn Declaration
     */
    public SuppressedChecks(final ClassOrInterfaceDeclaration dclrtn) {
        this.declaration = dclrtn;
    }

    // @checkstyle ReturnCountCheck (40 lines).
    @Override
    @SuppressWarnings({"PMD.OnlyOneReturn", "PMD.AvoidDeeplyNestedIfStmts"})
    public List<String> value() {
        final Optional<AnnotationExpr> annotation =
            this.declaration.getAnnotationByName("SuppressWarnings");
        if (annotation.isPresent()) {
            final AnnotationExpr expr = annotation.get();
            final Optional<SingleMemberAnnotationExpr> single =
                expr.toSingleMemberAnnotationExpr();
            if (single.isPresent()) {
                final Expression members = single.get().getMemberValue();
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
        return new ListOf<>();
    }
}

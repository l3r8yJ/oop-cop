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
import com.github.javaparser.ast.expr.SimpleName;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.extensions.ManySuppressionsDeclaration;
import ru.l3r8y.extensions.ParserDeclaration;
import java.util.List;

/**
 * Test case for {@link SuppressedChecks}.
 *
 * @since 0.3.6
 */
final class SuppressedChecksTest {

    @Test
    @ExtendWith(ParserDeclaration.class)
    void parsesSuppression(final ClassOrInterfaceDeclaration declaration) {
        final String suppress = "OOP.ErSuffixCheck";
        final List<String> found = new SuppressedChecks(declaration).value();
        final ListOf<String> expected = new ListOf<>(suppress);
        MatcherAssert.assertThat(
            String.format(
                "Suppressed checks %s do not match with expected format %s",
                found,
                expected
            ),
            found,
            new IsEqual<>(
                expected
            )
        );
    }

    @Test
    @ExtendWith(ManySuppressionsDeclaration.class)
    void parsesManySuppressions(final ClassOrInterfaceDeclaration declaration) {
        final List<String> found = new SuppressedChecks(declaration).value();
        final ListOf<String> expected = new ListOf<>(
            "OOP.MutableStateCheck",
            "OOP.ErSuffixCheck"
        );
        MatcherAssert.assertThat(
            String.format(
                "Suppressed checks %s do not match with expected format %s",
                found,
                expected
            ),
            found,
            new IsEqual<>(
                expected
            )
        );
    }

    @Test
    void parsesWithNoSuppression() {
        final ClassOrInterfaceDeclaration declaration =
            new ClassOrInterfaceDeclaration();
        declaration.setName(new SimpleName("Parser"));
        final List<String> found = new SuppressedChecks(declaration).value();
        MatcherAssert.assertThat(
            String.format(
                "Suppressed checks %s are not empty as expected",
                found
            ),
            found.isEmpty(),
            new IsEqual<>(true)
        );
    }
}

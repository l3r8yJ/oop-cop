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
package ru.l3r8y.complaint;

import java.nio.file.Path;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.ClassName;
import ru.l3r8y.extensions.ValidClass;
import ru.l3r8y.parser.ParsedClassName;
import ru.l3r8y.rule.ErSuffixCheck;

/**
 * Test case for {@link ClassifiedComplaint}.
 *
 * @since 0.3.7
 */
final class ClassifiedComplaintTest {

    @Test
    @ExtendWith(ValidClass.class)
    void complainsWithCheckClassification(final Path clazz) {
        final String name = "Test.java";
        final ClassName parsed = new ParsedClassName(name, clazz);
        final Class<ErSuffixCheck> check = ErSuffixCheck.class;
        final String explanation = "explanations..";
        final String expected = String.format(
            "'%s': '%s' has bad naming, %s (%s)",
            parsed.path(),
            name,
            explanation,
            check.getSimpleName()
        );
        final String classified = new ClassifiedComplaint(
            new WrongClassNaming(
                parsed,
                explanation
            ),
            check
        ).message();
        MatcherAssert.assertThat(
            String.format(
                "Complaint %s does not match with expected format %s",
                classified,
                expected
            ),
            classified,
            new IsEqual<>(expected)
        );
    }
}

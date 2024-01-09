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

import java.util.List;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link IsSuppressed}.
 *
 * @since 0.2.6
 */
final class IsSuppressedTest {

    @Test
    void suppressesCheck() {
        final String check = "ErSuffixCheck";
        final List<String> checks = new ListOf<>(
            String.format("OOP.%s", check)
        );
        MatcherAssert.assertThat(
            String.format(
                "Check %s is not suppressed, suppressed checks %s",
                check,
                checks
            ),
            new IsSuppressed(
                checks,
                () -> new ListOf<>(check)
            ).value(),
            new IsEqual<>(true)
        );
    }

    @Test
    void returnsFalseOnNotSuppressedCheck() {
        final String check = "MutableStateCheck";
        final List<String> checks = new ListOf<>("OOP.ErSuffixCheck");
        MatcherAssert.assertThat(
            String.format(
                "Check %s is not suppressed, suppressed checks %s",
                check,
                checks
            ),
            new IsSuppressed(
                checks,
                () -> new ListOf<>(check)
            ).value(),
            new IsEqual<>(false)
        );
    }
}

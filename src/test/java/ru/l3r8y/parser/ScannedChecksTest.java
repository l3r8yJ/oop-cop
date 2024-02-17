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

import java.util.Set;
import org.cactoos.text.FormattedText;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.Throws;

/**
 * Test suite for {@link ScannedChecks}.
 *
 * @since 0.2.7
 */
final class ScannedChecksTest {

    @Test
    @SuppressWarnings("JTCOP.RuleAssertionMessage")
    void returnsAllChecksInPackage() {
        final int size = 7;
        final String pack = "ru.l3r8y.checks";
        final Set<String> checks = new ScannedChecks(pack).value();
        new Assertion<>(
            new FormattedText(
                "%s should contain each Check implementation from 'ru.l3r8y.checks' package",
                checks
            ).toString(),
            checks,
            new HasSize(size)
        ).affirm();
    }

    @Test
    @SuppressWarnings("JTCOP.RuleAssertionMessage")
    void throwsOnCollectionChange() {
        final Set<String> checks = new ScannedChecks().value();
        new Assertion<>(
            new FormattedText(
            "Should throw exception when trying to modify: \n\t%s",
                checks
            ).toString(),
            () -> checks.add("123"),
            new Throws<>(UnsupportedOperationException.class)
        ).affirm();
    }
}

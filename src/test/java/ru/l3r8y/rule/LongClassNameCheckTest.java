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
package ru.l3r8y.rule;

import java.nio.file.Path;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.extensions.LongNamedClass;
import ru.l3r8y.extensions.SuppressedLongClassName;
import ru.l3r8y.extensions.ValidClass;

/**
 * Test case for {@link ru.l3r8y.rule.LongClassNameCheck}.
 *
 * @since 0.2.0
 */
final class LongClassNameCheckTest {

    @Test
    @ExtendWith(LongNamedClass.class)
    void failsWithErOnEnd(final Path clazz) {
        MatcherAssert.assertThat(
            "Build is not failed as expected",
            new CompositeClassName(clazz, 15).complaints(),
            Matchers.not(Matchers.empty())
        );
    }

    @Test
    @ExtendWith(ValidClass.class)
    void passesWhenNameIsFine(final Path clazz) {
        MatcherAssert.assertThat(
            "Build is not successed as expected",
            new CompositeClassName(clazz, 13).complaints(),
            Matchers.empty()
        );
    }

    @Test
    @ExtendWith(SuppressedLongClassName.class)
    void passesWhenSuppressed(final Path clazz) {
        MatcherAssert.assertThat(
            "Suppressed class has complaints, but it shouldn't",
            new CompositeClassName(clazz, 12).complaints(),
            Matchers.empty()
        );
    }
}

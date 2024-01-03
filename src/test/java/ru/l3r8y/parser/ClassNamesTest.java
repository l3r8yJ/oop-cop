/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2024. Ivanchuck Ivan.
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

import java.nio.file.Path;
import java.util.Collection;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.ClassName;
import ru.l3r8y.extensions.InvalidClass;
import ru.l3r8y.extensions.SuppressedWorker;

/**
 * Test cases for {@link ClassNames}.
 *
 * @since 0.2.6
 */
final class ClassNamesTest {

    @Test
    @ExtendWith(InvalidClass.class)
    void parsesClassNamesInRightFormat(final Path clazz) {
        final String name = new ListOf<>(
            new ClassNames(clazz).all()
        ).get(0).value();
        MatcherAssert.assertThat(
            String.format(
                "Class name %s is not valid, expected %s",
                name,
                InvalidClass.class.getSimpleName()
            ),
            name,
            new IsEqual<>("InvalidClass")
        );
    }

    @Test
    @ExtendWith(SuppressedWorker.class)
    void skipsSuppressedWorker(final Path clazz) {
        final int expected = 0;
        final Collection<ClassName> names = new ClassNames(clazz).all();
        MatcherAssert.assertThat(
            String.format(
                "Class names %s size is not valid: %s, expected %s",
                names,
                names.size(),
                expected
            ),
            names.size(),
            new IsEqual<>(expected)
        );
    }
}

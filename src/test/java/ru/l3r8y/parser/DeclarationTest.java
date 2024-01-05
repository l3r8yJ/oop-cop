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
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.ClassName;
import ru.l3r8y.extensions.ValidClass;

/**
 * Test case for {@link Declaration}.
 *
 * @since 0.3.6
 */
final class DeclarationTest {

    @Test
    @ExtendWith(ValidClass.class)
    void declaresClass(final Path clazz) throws IOException {
        final List<ClassName> names = new ListOf<>();
        StaticJavaParser.parse(clazz)
            .getChildNodes()
            .forEach(
                node -> new Declaration(
                    new Default(names, clazz),
                    node
                ).declare()
            );
        final String name = names.get(0).value();
        final String expected = "ValidClass";
        MatcherAssert.assertThat(
            String.format(
                "Class name %s does not match with expected one %s",
                name,
                expected
            ),
            name,
            new IsEqual<>(expected)
        );
    }

}

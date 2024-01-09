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
package ru.l3r8y.checks;

import java.nio.file.Path;
import java.util.Collection;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.l3r8y.Complaint;
import ru.l3r8y.extensions.ErNamedClass;
import ru.l3r8y.extensions.IsSuppressedErSuffix;
import ru.l3r8y.extensions.ValidClass;

/**
 * Test case for {@link CompositeErNamed}.
 *
 * @since 0.1.6
 */
final class CompositeErNamedTest {

    @Test
    @ExtendWith(ErNamedClass.class)
    void failsWithErOnEnd(final Path clazz) {
        MatcherAssert.assertThat(
            "Will fail with bad name",
            new CompositeErNamed(clazz).complaints(),
            Matchers.not(Matchers.empty())
        );
    }

    @Test
    @ExtendWith(ValidClass.class)
    void passesWhenNameIsFine(final Path clazz) {
        MatcherAssert.assertThat(
            "Ok when class name without 'er' suffix",
            new CompositeErNamed(clazz).complaints(),
            Matchers.empty()
        );
    }

    @Test
    @ExtendWith(IsSuppressedErSuffix.class)
    void passesSuppressedWorker(final Path clazz) {
        final Collection<Complaint> complaints =
            new CompositeErNamed(clazz).complaints();
        final int expected = 0;
        MatcherAssert.assertThat(
            String.format(
                "Complaints %s are not the expected (%s) size",
                complaints,
                expected
            ),
            complaints.size(),
            new IsEqual<>(expected)
        );
    }
}

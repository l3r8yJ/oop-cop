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

package ru.l3r8y.complaint;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import ru.l3r8y.Complaint;
import ru.l3r8y.parser.ParsedMethod;

/**
 * Test case for {@link BulkComplaint}.
 *
 * @since 0.1.4
 */
class BulkComplaintTest {

    /**
     * Separator for test.
     */
    private static final Pattern SEP = Pattern.compile("<sep>");

    @Test
    void mergesMessages() {
        final Collection<Complaint> complaints = Collections.nCopies(
            5,
            new WrongMethodSignature(
                new ParsedMethod(
                    "ClassName",
                    "myCoolMethod()",
                    "{ return null; }",
                    Paths.get("")
                ),
                "some cool explanation!<sep>"
            )
        );
        MatcherAssert.assertThat(
            "Length before equals length after",
            BulkComplaintTest.SEP.split(new BulkComplaint(complaints).message()).length,
            Matchers.equalTo(complaints.size())
        );
    }
}

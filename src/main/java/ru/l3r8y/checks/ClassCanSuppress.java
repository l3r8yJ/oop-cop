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

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;

/**
 * Check names that can be suppressed on class-level.
 *
 * @since 0.3.7
 */
@RequiredArgsConstructor
public final class ClassCanSuppress implements Scalar<List<String>> {

    @Override
    public List<String> value() {
        return new ListOf<>(
            ErSuffixCheck.class.getSimpleName(),
            LongClassNameCheck.class.getSimpleName(),
            MutableStateCheck.class.getSimpleName()
        );
    }
}

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

import lombok.RequiredArgsConstructor;
import ru.l3r8y.ClassName;
import ru.l3r8y.Complaint;

@RequiredArgsConstructor
public final class ClassNamedWithEr implements Complaint {

    private final ClassName clazz;

    private final String explanation;

    @Override
    public String message() {
        return String.format(
            "'%s':\nClass %s named with '-er' suffix, %s\n",
            this.clazz.path().toString(),
            this.clazz.value(),
            this.explanation
        );
    }
}

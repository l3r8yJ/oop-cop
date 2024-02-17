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
package ru.l3r8y.extensions;

import java.nio.file.Path;
import java.util.Objects;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.l3r8y.fake.FakeClass;

/**
 * It's a `ParameterResolver` that resolves `Path` parameters to the path of the
 * file `ValidClass.java`.
 *
 * @since 0.1.0
 */
public final class ValidClass implements ParameterResolver {

    @Override
    public boolean supportsParameter(
        final ParameterContext pctx,
        final ExtensionContext ectx
    ) {
        return Objects.equals(pctx.getParameter().getType(), Path.class);
    }

    @Override
    public Object resolveParameter(
        final ParameterContext pctx,
        final ExtensionContext ectx
    ) {
        return new FakeClass("ValidClass.java").asPath();
    }
}

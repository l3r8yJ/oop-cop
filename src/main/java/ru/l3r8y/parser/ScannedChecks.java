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

import com.google.common.reflect.ClassPath;
import com.jcabi.aspects.Loggable;
import java.util.List;
import java.util.stream.Collectors;
import org.cactoos.Scalar;

/**
 * Just the bulk of scanned checks.
 *
 * @since 0.2.7
 */
@Loggable(Loggable.DEBUG)
public final class ScannedChecks implements Scalar<List<String>> {

    /**
     * The package to scan.
     */
    private final String pckg;

    /**
     * Default ctor.
     */
    public ScannedChecks() {
        this("ru.l3r8y.checks");
    }

    /**
     * Primary ctor.
     *
     * @param pckg Package name to scan
     */
    public ScannedChecks(final String pckg) {
        this.pckg = pckg;
    }

    @Override
    public List<String> value() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
            .getAllClasses()
            .stream()
            .filter(clazz -> clazz.getPackageName().equalsIgnoreCase(this.pckg))
            .map(ClassPath.ClassInfo::getSimpleName)
            .filter(name -> !name.endsWith("Test") && !name.contains("package"))
            .collect(Collectors.toList());
    }
}

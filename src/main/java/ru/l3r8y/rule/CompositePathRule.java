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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import ru.l3r8y.Complaint;
import ru.l3r8y.Method;
import ru.l3r8y.Rule;
import ru.l3r8y.parser.ClassMethods;

/**
 * It's a rule that matches a path that is composed of other rules.
 *
 * @since 0.1.0
 */
@AllArgsConstructor
public final class CompositePathRule implements Rule {

    /**
     * A path to the root of the project.
     */
    private final Path start;

    @Override
    public List<Complaint> complaints() {
        final List<Complaint> accum;
        if (Files.exists(this.start)) {
            try (Stream<Path> files = Files.walk(this.start)) {
                accum = files
                    .filter(Files::exists)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .map(ClassMethods::new)
                    .map(ClassMethods::all)
                    .map(CompositePathRule::checkAssigment)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            } catch (final IOException ex) {
                throw new IllegalStateException(
                    String.format(
                        "Error occupied while checking java sources: %s\n",
                        ex.getMessage()
                    ),
                    ex
                );
            }
        } else {
            accum = Collections.emptyList();
        }
        return accum;
    }

    /**
     * It takes a collection of methods, and returns a collection of complaints.
     *
     * @param methods A collection of methods to check
     * @return A collection of complaints
     */
    private static Collection<Complaint> checkAssigment(final Collection<Method> methods) {
        final Collection<Complaint> result;
        if (methods.isEmpty()) {
            result = Collections.emptyList();
        } else {
            final Collection<Complaint> cmps = new ArrayList<>(0);
            methods.stream()
                .map(MethodContainsAssigment::new)
                .map(MethodContainsAssigment::complaints)
                .forEach(cmps::addAll);
            result = cmps;
        }
        return result;
    }
}

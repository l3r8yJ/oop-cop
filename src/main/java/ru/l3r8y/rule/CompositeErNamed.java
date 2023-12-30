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
import lombok.RequiredArgsConstructor;
import ru.l3r8y.ClassName;
import ru.l3r8y.Complaint;
import ru.l3r8y.Rule;
import ru.l3r8y.parser.ClassNames;

/*
* @todo #35 Remove code duplications.
* The CompositeErNamedClass#complaints
* and CompositeErNamedClass#checkWithErNamedClassRule
* are code duplication from CompositeMethodsContainsAssignment.
* Refactoring is necessary.
* */
/**
 * Checks all java files with {@link ErNamedClass}.
 *
 * @since 0.1.6
 */
@RequiredArgsConstructor
public final class CompositeErNamed implements Rule {

    /**
     * The start path.
     */
    private final Path start;

    @Override
    public Collection<Complaint> complaints() {
        final List<Complaint> accum;
        if (Files.exists(this.start)) {
            try (Stream<Path> files = Files.walk(this.start)) {
                accum = files
                    .filter(Files::exists)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .map(ClassNames::new)
                    .map(ClassNames::all)
                    .map(CompositeErNamed::checkWithErNamedRule)
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
     * Checks with {@link ErNamedClass}.
     *
     * @param names Collection of class names.
     * @return Collection of complaints.
     */
    private static Collection<Complaint> checkWithErNamedRule(final Collection<ClassName> names) {
        final Collection<Complaint> result;
        if (names.isEmpty()) {
            result = Collections.emptyList();
        } else {
            final Collection<Complaint> cmps = new ArrayList<>(0);
            names.stream()
                .map(ErNamedClass::new)
                .map(ErNamedClass::complaints)
                .forEach(cmps::addAll);
            result = cmps;
        }
        return result;
    }
}

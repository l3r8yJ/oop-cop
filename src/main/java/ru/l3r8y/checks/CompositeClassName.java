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
import ru.l3r8y.Check;
import ru.l3r8y.ClassName;
import ru.l3r8y.Complaint;
import ru.l3r8y.parser.ClassNames;

/**
 * Composite Long Class Name.
 *
 * @since 0.2.0
 */
@RequiredArgsConstructor
public final class CompositeClassName implements Check {

    /**
     * Path to start.
     */
    private final Path start;

    /**
     * Length to pass.
     */
    private final int fine;

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
                    .map(this::checkLongName)
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

    /*
     * @todo #81 Introduce new class from #checkLongName private method.
     *  we should introduce new reusable class from private method
     *  #checkLongName.
     *  Don't forget to remove this puzzle.
     */
    /**
     * Checks long class name in a collection of names.
     * @param names Class names
     * @return Complaints.
     */
    private Collection<Complaint> checkLongName(final Collection<ClassName> names) {
        final Collection<Complaint> result;
        if (names.isEmpty()) {
            result = Collections.emptyList();
        } else {
            final Collection<Complaint> cmps = new ArrayList<>(0);
            names.stream()
                .map(cl -> new LongClassNameCheck(cl, this.fine))
                .map(LongClassNameCheck::complaints)
                .forEach(cmps::addAll);
            result = cmps;
        }
        return result;
    }
}

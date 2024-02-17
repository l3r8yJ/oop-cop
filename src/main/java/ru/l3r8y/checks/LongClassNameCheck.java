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
package ru.l3r8y.checks;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import ru.l3r8y.Check;
import ru.l3r8y.ClassName;
import ru.l3r8y.Complaint;
import ru.l3r8y.complaint.ClassifiedComplaint;
import ru.l3r8y.complaint.LinkedComplaint;
import ru.l3r8y.complaint.WrongClassNaming;

/**
 * Check for Long class name.
 *
 * @since 0.2.0
 */
@RequiredArgsConstructor
public final class LongClassNameCheck implements Check {

    /**
     * Class name.
     */
    private final ClassName name;

    /**
     * Length to pass.
     */
    private final int fine;

    @Override
    public Collection<Complaint> complaints() {
        return new ConditionCheck(
            this::longerThanOk,
            new ClassifiedComplaint(
                new LinkedComplaint(
                    new WrongClassNaming(
                        this.name,
                        String.format(
                            "class name is more than %s, consider more simple name",
                            this.fine
                        )
                    ),
                    "https://www.yegor256.com/2015/01/12/compound-name-is-code-smell.html"
                ),
                this.getClass()
            )
        ).complaints();
    }

    /*
     * @todo #81 Introduce new class from #longerThanOk private method.
     *  we should introduce new reusable class from private method
     *  #longerThanOk.
     *  Don't forget to remove this puzzle.
     */

    /**
     * Is name ok?
     *
     * @return True if longer
     */
    private boolean longerThanOk() {
        return this.name.value().length() > this.fine;
    }
}

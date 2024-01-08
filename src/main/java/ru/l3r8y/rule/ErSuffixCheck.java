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

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import ru.l3r8y.ClassName;
import ru.l3r8y.Complaint;
import ru.l3r8y.Rule;
import ru.l3r8y.complaint.ClassifiedComplaint;
import ru.l3r8y.complaint.LinkedComplaint;
import ru.l3r8y.complaint.WrongClassNaming;

/**
 * It checks if class is -er named.
 *
 * @since 0.1.6
 */
@RequiredArgsConstructor
public final class ErSuffixCheck implements Rule {

    /**
     * Blog post link.
     */
    private static final String ER_BLOG_POST =
        "https://www.yegor256.com/2015/03/09/objects-end-with-er.html";

    /**
     * The name of class to check.
     */
    private final ClassName name;

    @Override
    public Collection<Complaint> complaints() {
        return new ConditionRule(
            this::isEndsWithEr,
            new ClassifiedComplaint(
                new LinkedComplaint(
                    new WrongClassNaming(
                        this.name,
                        "class ends with '-er' suffix, it's prohibited"
                    ),
                    ErSuffixCheck.ER_BLOG_POST
                ),
                this.getClass()
            )
        ).complaints();
    }

    /**
     * Is ends with -er.
     *
     * @return True if ends with '-er'.
     */
    private boolean isEndsWithEr() {
        return this.name.value().endsWith("er");
    }
}

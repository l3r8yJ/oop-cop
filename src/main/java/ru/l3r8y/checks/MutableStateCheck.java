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
/*
 * #22:15m/DEV Message about annotation.
 * Suggest removing mutability of class or mark class with {@code @Mutable}.
 */
package ru.l3r8y.checks;

import java.util.Collection;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import ru.l3r8y.Check;
import ru.l3r8y.Complaint;
import ru.l3r8y.Method;
import ru.l3r8y.complaint.LinkedComplaint;
import ru.l3r8y.complaint.WrongMethodSignature;

/**
 * It checks if a method contains an assignment.
 *
 * @since 0.1.0
 */
@AllArgsConstructor
public final class MutableStateCheck implements Check {

    /**
     * Pattern which matches this assignment.
     * <p>
     *  {@code this.field = newValue;}
     * </p>
     */
    private static final Pattern PATTERN = Pattern.compile("this\\.[a-zA-Z_]\\w*\\s*=\\s*.+?;");

    /**
     * The method to check.
     */
    private final Method method;

    @Override
    public Collection<Complaint> complaints() {
        return new ConditionCheck(
            this::containsAssigment,
            new LinkedComplaint(
                new WrongMethodSignature(
                    this.method,
                    "method body contains an assignment, setters violates OOP principles"
                ),
                "https://www.l3r8y.ru/2023/03/17/hands-off-the-state-of-the-object"
            )
        ).complaints();
    }

    /**
     * If the method body contains an assignment, return true.
     *
     * @return A boolean value.
     */
    private boolean containsAssigment() {
        return MutableStateCheck.PATTERN.matcher(this.method.body()).find();
    }
}

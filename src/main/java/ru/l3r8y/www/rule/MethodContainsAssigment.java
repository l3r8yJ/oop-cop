package ru.l3r8y.www.rule;

import java.util.Collection;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import ru.l3r8y.www.Complaint;
import ru.l3r8y.www.Method;
import ru.l3r8y.www.Rule;
import ru.l3r8y.www.complaint.WrongMethodSignature;

@AllArgsConstructor
public final class MethodContainsAssigment implements Rule {

    private static final Pattern PATTERN = Pattern.compile("this\\.[a-zA-Z_]\\w*\\s*=\\s*.+?;\n");
    private final Method method;

    @Override
    public Collection<Complaint> complaints() {
        return new ConditionRule(
            this::containsAssigment,
            new WrongMethodSignature(
                this.method,
                "method body contains an assignment, setters violates OOP principles"
            )
        ).complaints();
    }

    private boolean containsAssigment() {
        return MethodContainsAssigment.PATTERN.matcher(this.method.body()).find();
    }
}

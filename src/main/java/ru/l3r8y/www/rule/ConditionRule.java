package ru.l3r8y.www.rule;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import ru.l3r8y.www.Complaint;
import ru.l3r8y.www.Rule;

@AllArgsConstructor
public final class ConditionRule implements Rule {

    private final Supplier<Boolean> predicate;

    private final Complaint complaint;

    @Override
    public Collection<Complaint> complaints() {
        final Collection<Complaint> accum;
        if (this.predicate.get()) {
            accum = Collections.singleton(this.complaint);
        } else {
            accum = Collections.emptyList();
        }
        return accum;
    }
}

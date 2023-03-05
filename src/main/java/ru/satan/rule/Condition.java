package ru.satan.rule;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;
import ru.satan.Complaint;
import ru.satan.Rule;

public final class Condition implements Rule {

    private final Supplier<Boolean> predicate;

    private final Complaint complaint;

    Condition(final Supplier<Boolean> predicate, final Complaint complaint) {
        this.predicate = predicate;
        this.complaint = complaint;
    }

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

package ru.satan.complaint;

import java.util.Collection;
import java.util.stream.Collectors;
import ru.satan.Complaint;

public final class CompoundComplaint implements Complaint {

    private final Collection<? extends Complaint> complaints;

    public CompoundComplaint(final Collection<? extends Complaint> complaints) {
        this.complaints = complaints;
    }

    @Override
    public String message() {
        return this.complaints.stream()
            .map(Complaint::message)
            .collect(Collectors.joining("\n", "\n", ""));
    }
}

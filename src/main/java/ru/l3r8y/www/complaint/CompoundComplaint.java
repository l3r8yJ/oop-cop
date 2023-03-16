package ru.l3r8y.www.complaint;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ru.l3r8y.www.Complaint;

@AllArgsConstructor
public final class CompoundComplaint implements Complaint {

    private final Collection<? extends Complaint> complaints;

    @Override
    public String message() {
        return this.complaints.stream()
            .map(Complaint::message)
            .collect(Collectors.joining("\n", "\n", ""));
    }
}

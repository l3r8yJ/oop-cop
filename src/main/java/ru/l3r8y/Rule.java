package ru.l3r8y;

import java.util.Collection;

@FunctionalInterface
public interface Rule {

    Collection<Complaint> complaints();

}

package ru.l3r8y.www;

import java.util.Collection;

@FunctionalInterface
public interface Rule {

    Collection<Complaint> complaints();

}

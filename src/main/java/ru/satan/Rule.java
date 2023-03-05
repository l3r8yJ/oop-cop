package ru.satan;

import java.util.Collection;

@FunctionalInterface
public interface Rule {

    Collection<Complaint> complaints();

}

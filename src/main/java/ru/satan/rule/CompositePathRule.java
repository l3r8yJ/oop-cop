package ru.satan.rule;

import com.jcabi.log.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.satan.Complaint;
import ru.satan.Method;
import ru.satan.Rule;
import ru.satan.parser.ClassMethods;

public final class CompositePathRule implements Rule {

    private final Path start;

    public CompositePathRule(final Path start) {
        this.start = start;
    }

    @Override
    public Collection<Complaint> complaints() {
        final Collection<Complaint> accum;
        if (Files.exists(this.start)) {
            try(final Stream<Path> files = Files.walk(this.start)) {
                accum = files
                    .filter(Files::exists)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .map(ClassMethods::new)
                    .map(ClassMethods::all)
                    .map(CompositePathRule::checkAssigment)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            } catch (final IOException ex) {
                throw new IllegalStateException(ex);
            }
        } else {
            Logger.info(this, "DICKDICKDICK");
            accum = Collections.emptyList();
        }
        return accum;
    }

    private static Collection<Complaint> checkAssigment(final Collection<Method> methods) {
        if (methods.isEmpty()) {
            throw new IllegalStateException("Project doesn't contains any methods");
        }
        final Collection<Complaint> cmps = new ArrayList<>(0);
        methods.stream()
            .map(MethodContainsAssigment::new)
            .map(MethodContainsAssigment::complaints)
            .forEach(cmps::addAll);
        return cmps;
    }
}

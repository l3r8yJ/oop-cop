package ru.satan.complaint;

import ru.satan.Complaint;
import ru.satan.Method;

public class WrongMethodSignature implements Complaint {

    private final Method method;

    private final String explanation;

    public WrongMethodSignature(final Method method, final String explanation) {
        this.method = method;
        this.explanation = explanation;
    }

    @Override
    public final String message() {
        return String.format(
            "'%s': Method '%s#%s' has wrong method signature, because %s",
            this.method.path(),
            this.method.className(),
            this.method.name(),
            this.explanation
        );
    }
}

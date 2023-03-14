package ru.satan.extensions;

import java.nio.file.Path;
import java.util.Objects;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.satan.fake.FakeClass;

public class CaseWithoutThis implements ParameterResolver {

    @Override
    public boolean supportsParameter(
        final ParameterContext pctx,
        final ExtensionContext ectx
    ) {
        return Objects.equals(pctx.getParameter().getType(), Path.class);
    }

    @Override
    public Object resolveParameter(
        final ParameterContext pctx,
        final ExtensionContext ectx
    ) {
        return new FakeClass("CaseWithoutThis.java").asPath();
    }

}

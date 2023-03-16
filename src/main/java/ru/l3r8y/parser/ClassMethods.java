package ru.l3r8y.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import ru.l3r8y.Method;
import ru.l3r8y.Methods;

@AllArgsConstructor
public final class ClassMethods implements Methods {

    private final Path path;

    @Override
    public Collection<Method> all() {
        final Collection<Method> methods = new ArrayList<>(0);
        try {
            final CompilationUnit parsed = StaticJavaParser.parse(this.path);
            for (final Node node : parsed.getChildNodes()) {
                if (node instanceof ClassOrInterfaceDeclaration) {
                    this.transferMethods(methods, (ClassOrInterfaceDeclaration) node);
                }
            }
        } catch (final IOException ex) {
            throw new IllegalStateException(
                String.format("Unable to get all methods: %s", ex.getMessage()),
                ex
            );
        }
        return methods;
    }

    private void transferMethods(
        final Collection<Method> methods,
        final ClassOrInterfaceDeclaration node
    ) {
        for (final MethodDeclaration mtd : node.getMethods()) {
            methods.add(
                new ParsedMethod(
                    node.getNameAsString(),
                    mtd.getNameAsString(),
                    mtd.getBody().orElseThrow().toString(),
                    this.path
                )
            );
        }
    }
}

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023. Ivanchuck Ivan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ru.l3r8y;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import ru.l3r8y.checks.AssignmentCheck;
import ru.l3r8y.checks.CompositeClassName;
import ru.l3r8y.checks.CompositeErNamed;
import ru.l3r8y.checks.LongClassNameCheck;
import ru.l3r8y.complaint.BulkComplaint;

/**
 * It's a Maven plugin that runs a set of rules against the source code of the
 * project and fails the build if any of the rules are violated.
 *
 * @since 0.1.0
 */
@Mojo(name = "search", defaultPhase = LifecyclePhase.VALIDATE)
public final class ValidateMojo extends AbstractMojo {

    /**
     * The project.
     */
    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    /**
     * The fail on error.
     *
     * @checkstyle MemberNameCheck (6 lines).
     */
    @SuppressWarnings("PMD.ImmutableField")
    @Parameter(defaultValue = "true")
    private boolean failOnError = true;

    /**
     * Length of Class Name.
     *
     * @see LongClassNameCheck
     * @checkstyle MemberNameCheck (6 lines).
     */
    @SuppressWarnings("PMD.ImmutableField")
    @Parameter
    private int maxClassNameLen = 13;

    @Override
    public void execute() throws MojoFailureException {
        final Path start = Paths.get(this.project.getCompileSourceRoots().get(0));
        final List<Complaint> complaints = new ArrayList<>(0);
        complaints.addAll(new AssignmentCheck(start).complaints());
        complaints.addAll(new CompositeErNamed(start).complaints());
        complaints.addAll(
            new CompositeClassName(
                start, this.maxClassNameLen
            ).complaints()
        );
        if (!complaints.isEmpty() && this.failOnError) {
            throw new MojoFailureException(new BulkComplaint(complaints).message());
        }
        if (complaints.isEmpty()) {
            this.getLog().info("Violations not found");
        } else {
            complaints.forEach(complaint -> this.getLog().warn(complaint.message()));
        }
    }
}

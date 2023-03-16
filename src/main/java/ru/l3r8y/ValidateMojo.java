package ru.l3r8y;

import java.nio.file.Paths;
import java.util.Collection;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import ru.l3r8y.complaint.CompoundComplaint;
import ru.l3r8y.rule.CompositePathRule;

@Mojo(name = "search", defaultPhase = LifecyclePhase.VALIDATE)
public final class ValidateMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "true")
    private boolean failOnError = true;

    @Override
    public void execute() throws MojoFailureException {
        this.getLog().info("Running Satan plugin");
        final Collection<Complaint> complaints = new CompositePathRule(
            Paths.get(this.project.getCompileSourceRoots().get(0))
        ).complaints();
        if (!complaints.isEmpty() && this.failOnError) {
            throw new MojoFailureException(new CompoundComplaint(complaints).message());
        } else if (!complaints.isEmpty()) {
            complaints.forEach(complaint -> this.getLog().warn(complaint.message()));
        } else {
            this.getLog().info("Violations not found");
        }
    }
}

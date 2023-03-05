package ru.satan;

import com.jcabi.log.Logger;
import java.nio.file.Paths;
import java.util.Collection;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import ru.satan.complaint.CompoundComplaint;
import ru.satan.rule.CompositePathRule;

@Mojo(name = "check", defaultPhase = LifecyclePhase.VALIDATE)
public final class ValidateMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Parameter(defaultValue = "true")
    private boolean failOnError = true;

    @Override
    public void execute() throws MojoFailureException {
        this.getLog().info("Running Satan plugin");
        Logger.info(this, "SRC: " + this.project.getCompileSourceRoots().get(0));
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

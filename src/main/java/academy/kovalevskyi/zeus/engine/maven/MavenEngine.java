package academy.kovalevskyi.zeus.engine.maven;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class MavenEngine {

  private static final Model MAVEN_POM_FILE;

  static {
    try {
      final var inputSteam = MavenEngine.class.getResourceAsStream("/pom.xml");
      MAVEN_POM_FILE = new MavenXpp3Reader().read(inputSteam);
    } catch (XmlPullParserException | IOException exception) {
      throw new ExceptionInInitializerError(exception.getMessage());
    }
  }

  public static Model getConfig() {
    return MAVEN_POM_FILE;
  }

  public static int execute(final File maven, final List<String> request)
      throws MavenInvocationException {
    final var invocationRequest = new DefaultInvocationRequest()
        .setInputStream(InputStream.nullInputStream())
        .setGoals(request);
    return new DefaultInvoker().setMavenHome(maven).execute(invocationRequest).getExitCode();
  }
}
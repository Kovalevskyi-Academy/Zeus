package academy.kovalevskyi.zeus.engine.maven;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class MavenEngine {

  private static final Model MAVEN_CONFIG;

  static {
    try {
      MAVEN_CONFIG = initialize();
    } catch (XmlPullParserException | IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Model getConfig() {
    return MAVEN_CONFIG;
  }


  public static int execute(final File maven, final Request request)
      throws MavenInvocationException {
    return execute(maven, request.getCommands());
  }

  public static int execute(final File maven, final List<String> request)
      throws MavenInvocationException {
    final var invocationRequest = new DefaultInvocationRequest()
        .setInputStream(null)
        .setGoals(request);
    return new DefaultInvoker().setMavenHome(maven).execute(invocationRequest).getExitCode();
  }

  private static Model initialize() throws XmlPullParserException, IOException {
    final var xpp3Reader = new MavenXpp3Reader();
    try (var inputStream = MavenEngine.class.getResourceAsStream("/pom.xml")) {
      return xpp3Reader.read(inputStream);
    } catch (IOException e) {
      try (var fileReader = new FileReader("pom.xml")) {
        return xpp3Reader.read(fileReader);
      }
    }
  }
}
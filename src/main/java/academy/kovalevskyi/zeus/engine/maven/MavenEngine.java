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

public final class MavenEngine {

  public static final String M2_HOME = System.getenv("M2_HOME");
  private static final Model ZEUS_CONFIG;
  private static final Model USER_CONFIG;

  static {
    try {
      ZEUS_CONFIG = initializeZeusConfig();
      USER_CONFIG = initializeUserConfig();
    } catch (XmlPullParserException | IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Model getConfig(final Configuration configuration) {
    return switch (configuration) {
      case USER -> USER_CONFIG;
      case ZEUS -> ZEUS_CONFIG;
    };
  }

  public static int execute(final File maven, final Request request)
      throws MavenInvocationException {
    if (request == null) {
      throw new IllegalArgumentException("Request is null");
    }
    return execute(maven, request.getCommands());
  }

  public static int execute(final File maven, final List<String> request)
      throws MavenInvocationException {
    if (maven == null && M2_HOME == null) {
      throw new MavenInvocationException("Configure maven on your system!");
    }
    if (request == null || request.isEmpty()) {
      throw new IllegalArgumentException("Commands list is null or empty");
    }
    final var invocationRequest = new DefaultInvocationRequest()
        .setInputStream(null)
        .setGoals(request);
    return new DefaultInvoker().setMavenHome(maven).execute(invocationRequest).getExitCode();
  }

  private static Model initializeZeusConfig() throws XmlPullParserException, IOException {
    try (var inputStream = MavenEngine.class.getResourceAsStream("/pom.xml")) {
      return new MavenXpp3Reader().read(inputStream);
    } catch (IOException e) {
      return initializeUserConfig();
    }
  }

  private static Model initializeUserConfig() throws XmlPullParserException, IOException {
    try (var fileReader = new FileReader("pom.xml")) {
      return new MavenXpp3Reader().read(fileReader);
    }
  }
}
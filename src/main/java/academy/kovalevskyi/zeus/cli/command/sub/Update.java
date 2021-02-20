package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.zeus.engine.maven.MavenEngine;
import academy.kovalevskyi.zeus.model.github.Asset;
import academy.kovalevskyi.zeus.model.github.Release;
import academy.kovalevskyi.zeus.service.DownloadManager;
import academy.kovalevskyi.zeus.service.ZeusRepo;
import academy.kovalevskyi.zeus.util.FileExplorer;
import academy.kovalevskyi.zeus.util.FileType;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import org.apache.maven.model.Model;
import picocli.CommandLine.Command;

@Command(
    name = "update",
    description = "Check and download the latest Zeus",
    mixinStandardHelpOptions = true)
public class Update implements Callable<Void> {

  private final Model config;

  public Update() {
    config = MavenEngine.getConfig();
  }

  @Override
  public Void call() throws Exception {
    final var release = ZeusRepo.getLatestRelease();
    if (isAvailableUpdate(config.getVersion(), release.getVersion())) {
      if (!release.getBody().isBlank()) {
        System.out.printf("What is new:%n%s%n", release.getBody());
      }
      var asset = getJarAsset(release);
      try (var manager = new DownloadManager(asset.getLink())) {
        manager.download();
      }
    } else {
      System.out.println("You already have the latest version of Zeus");
    }
    return null;
  }

  private Asset getJarAsset(final Release release) {
    for (var asset : release.getAssets()) {
      if (FileExplorer.match(asset.getName(), FileType.JAR)) {
        return asset;
      }
    }
    throw new NullPointerException("The latest Zeus release has no jar archive attachment");
  }

  private String parseVersion(final String version) {
    final var pattern = Pattern.compile("^[vV]?(\\d+(.\\d+)*)$");
    var matcher = pattern.matcher(version);
    if (matcher.find()) {
      return matcher.group(1);
    }
    throw new IllegalArgumentException(String.format("Illegal version format '%s'", version));
  }

  private boolean isAvailableUpdate(final String current, final String release) {
    final var currentSplits = parseVersion(current).split("\\.");
    final var releaseSplits = parseVersion(release).split("\\.");
    final var maxLength = Math.max(currentSplits.length, releaseSplits.length);
    for (var index = 0; index < maxLength; index++) {
      var v1 = index < currentSplits.length ? Integer.parseInt(currentSplits[index]) : 0;
      var v2 = index < releaseSplits.length ? Integer.parseInt(releaseSplits[index]) : 0;
      if (v1 != v2) {
        return v2 > v1;
      }
    }
    return false;
  }

}

package academy.kovalevskyi.zeus.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import academy.kovalevskyi.zeus.model.github.Release;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ZeusRepoTest {

  @Test
  public void testGetLatestRelease() {
    var release = (Release) null;
    try {
      release = ZeusRepo.getLatestRelease();
    } catch (IOException ignored) {
    }
    assumeTrue(release != null, "There is no release to test");

    assertNotNull(release.getBody());
    assertNotNull(release.getVersion());
    assertNotNull(release.getAssets());
    assertEquals(1, release.getAssets().length);

    var asset = release.getAssets()[0];
    assertNotNull(asset);
    assertNotNull(asset.getName());
    assertNotNull(asset.getLink());
  }
}
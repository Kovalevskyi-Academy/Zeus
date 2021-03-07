package academy.kovalevskyi.zeus.model.github;

import com.google.gson.annotations.SerializedName;

public final class Release {

  @SerializedName("tag_name")
  private String version;

  @SerializedName("body")
  private String body;

  @SerializedName("assets")
  private Asset[] assets;

  public Asset[] getAssets() {
    return assets;
  }

  public String getVersion() {
    return version;
  }

  public String getBody() {
    return body;
  }
}

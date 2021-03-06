package academy.kovalevskyi.zeus.model.github;

import com.google.gson.annotations.SerializedName;

public final class Release {

  @SerializedName("html_url")
  private String link;

  @SerializedName("name")
  private String name;

  @SerializedName("tag_name")
  private String version;

  @SerializedName("body")
  private String body;

  @SerializedName("assets")
  private Asset[] assets;

  public Asset[] getAssets() {
    return assets;
  }

  public String getLink() {
    return link;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public String getBody() {
    return body;
  }
}

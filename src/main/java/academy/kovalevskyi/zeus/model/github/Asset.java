package academy.kovalevskyi.zeus.model.github;

import com.google.gson.annotations.SerializedName;

public class Asset {

  @SerializedName("name")
  private String name;

  @SerializedName("browser_download_url")
  private String link;

  public String getName() {
    return name;
  }

  public String getLink() {
    return link;
  }
}

package academy.kovalevskyi.zeus.service;

import academy.kovalevskyi.zeus.model.github.Release;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ZeusRepo {

  private static final URL URL;

  static {
    try {
      URL = new URL("https://api.github.com/repos/Kovalevskyi-Academy/Zeus/releases/latest");
    } catch (MalformedURLException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Release getLatestRelease() throws IOException {
    var con = (HttpURLConnection) URL.openConnection();
    con.setConnectTimeout(3_000);
    con.setReadTimeout(3_000);
    con.setRequestProperty("accept", "application/vnd.github.v3+json");
    try {
      con.connect();
    } catch (IOException e) {
      throw new ConnectException("Some internet connection problems");
    }
    try (var reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
      var json = reader.readLine();
      con.disconnect();
      if (con.getResponseCode() == 200) {
        return new Gson().fromJson(json, Release.class);
      } else {
        throw new NullPointerException("No release or link is broken");
      }
    }
  }

}

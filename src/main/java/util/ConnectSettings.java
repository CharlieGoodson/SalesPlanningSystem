package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectSettings {

    private String url;
    private String src;

    public ConnectSettings() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties prts = new Properties();
        try {
            prts.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        url = prts.getProperty("url");
        src = prts.getProperty("src");
    }

    public String getUrl() {
        return url;
    }
    public String getSrc() {
        return src;
    }

}

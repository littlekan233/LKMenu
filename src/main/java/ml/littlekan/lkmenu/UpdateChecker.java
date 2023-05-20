package ml.littlekan.lkmenu;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {
    private String version = SharedVariable.version;
    private Logger logger;
    public UpdateChecker(Logger _l){
        logger = _l;
    }
    public UpdateCheckerTemplate check() throws IOException {
        URL api = new URL("https://api.github.com/repos/littlekan233/LKMenu/releases");
        HttpURLConnection connection = (HttpURLConnection) api.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.connect();
        if (connection.getResponseCode() == 200){
            InputStream stream = connection.getInputStream();
        }
    }
}

package ml.littlekan.lkmenu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateChecker {
    private String version = SharedVariable.version;
    private Logger logger;
    public UpdateChecker(Logger _l){
        logger = _l;
    }
    public List<UpdateCheckerTemplate> check() throws IOException {
        URL api = new URL("https://api.github.com/repos/littlekan233/LKMenu/releases");
        HttpURLConnection connection = (HttpURLConnection) api.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.connect();
        if (connection.getResponseCode() == 200){
            InputStream stream = connection.getInputStream();
            return new Gson().fromJson(new InputStreamReader(stream), new TypeToken<List<UpdateCheckerTemplate>>(){}.getType());
        }else{
            logger.warning("Couldn't check for updates!");
            logger.warning("Current version: "+version);
            logger.warning("Status code (Updater is using GitHub API): " + new StringBuilder(connection.getResponseCode()).toString());
            logger.warning("Message: \n"+connection.getResponseMessage());
        }
        return null;
    }

    public static long pubtimeToTimeStamp(String pubt) {
        try {
            return new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ").parse(pubt).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public static long pttts(String pubt){
        return pubtimeToTimeStamp(pubt);
    }
}

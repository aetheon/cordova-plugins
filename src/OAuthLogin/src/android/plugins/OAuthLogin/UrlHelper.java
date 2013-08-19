package plugins.OAuthLogin;

import java.net.URL;

/**
 * Created by britoo on 13/06/13.
 */
public class UrlHelper {

    public static String getHostHightLevelDomain(URL url){

        String hostname = url.getHost();
        String[] splited = hostname.split("\\.");

        if(splited.length<2) return null;

        String host = String.format("%s.%s", splited[splited.length-2], splited[splited.length-1]);
        return host;

    }
}

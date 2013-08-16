package com.custom.oauthlogin;

import java.util.HashMap;

/**
 * Created by britoo on 06/08/13.
 */
public class Cookie {

    public static HashMap<String, String> parseCookies(String cookies){

        HashMap<String, String> cookiesHash = new HashMap<String, String>();

        for(String cookie : cookies.split(";")){

            String[] pair = cookie.split("=");
            if(pair.length == 2){
                cookiesHash.put(pair[0].trim(), pair[1].trim());
            }

        }

        return cookiesHash;

    }

}

package com.jelcaf.pacomf.patealapalma.network.image;

/**
 * Created by Paco on 26/04/2015.
 */
public class Constant {
    /*
    Logging flag
   */
    public static final boolean LOGGING = true;

    /*
      Your imgur client id. You need this to upload to imgur.
      More here: https://api.imgur.com/
     */
    public static final String MY_IMGUR_CLIENT_ID = "e29cd388bb8b764";
    public static final String MY_IMGUR_CLIENT_SECRET = "4e46a425b115cae945d7f0f5d3944660599d764c";

    /*
      Redirect URL for android.
     */
    public static final String MY_IMGUR_REDIRECT_URL = "http://android";

    /*
      Client Auth
     */
    public static String getClientAuth(){
        return "Client-ID " + MY_IMGUR_CLIENT_ID;
    }
}

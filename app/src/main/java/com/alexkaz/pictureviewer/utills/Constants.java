package com.alexkaz.pictureviewer.utills;

public class Constants {

    public static final String APP_PREFS = "app_prefs";
    public static final String AUTHENTICATED = "authenticated";
    public static final String TOKEN = "token";

    public static final String CLIENT_ID = "3d3a8c1d059e839a230409c18bfef3e9cd51e378dc7abadc53e469162db1343e";
    public static final String REDIRECT_URI="urn:ietf:wg:oauth:2.0:oob";
    public static final String RESPONSE_TYPE="code";
    public static final String SCOPE="public";

    public static final String OAUTH2_URL="https://unsplash.com/oauth/authorize?" +
            "client_id=" + CLIENT_ID +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=" + RESPONSE_TYPE +
            "&scope=" + SCOPE;

    public static final String CLIENT_SECRET = "03e3b6093ef6b18b021cb44308b83ea7a7168594055616cad98a04a8a4526613";
    public static final String GRANT_TYPE="authorization_code";

    public static final String OAUTH2_TOKEN_URL = "https://unsplash.com/";
}

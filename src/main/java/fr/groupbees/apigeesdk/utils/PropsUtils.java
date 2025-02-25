package fr.groupbees.apigeesdk.utils;

import java.util.Properties;

public class PropsUtils {

    private static final String HOST = "%s_HOST";
    private static final String USER = "%s_USER";
    private static final String PWD = "%s_PWD";
    private static final String TOKEN = "%s_TOKEN";

    public static Properties fromEnv(final String env) {
        Properties props = new Properties();
        props.setProperty("env", env);

        final String HOST_ENV = String.format(HOST, env.toUpperCase());
        final String USER_ENV = String.format(USER, env.toUpperCase());
        final String PWD_ENV = String.format(PWD, env.toUpperCase());
        final String TOKEN_ENV = String.format(TOKEN, env.toUpperCase());

        final String MUST_NOT_BE_EMPTY = "%s must not be empty";

        String host = System.getenv(HOST_ENV);
        if (host == null || host.isBlank()) {
            Logger.error(String.format(MUST_NOT_BE_EMPTY, HOST_ENV));
            System.exit(1);
        }

   /* String user = System.getenv(USER_ENV);
    if (user == null || user.isBlank()) {
      Logger.error(String.format(MUST_NOT_BE_EMPTY, USER_ENV));
      System.exit(1);
    }

    String pwd = System.getenv(PWD_ENV);
    if (pwd == null || pwd.isBlank()) {
      Logger.error(String.format(MUST_NOT_BE_EMPTY, PWD_ENV));
      System.exit(1);
    }*/

        String token = System.getenv(TOKEN_ENV);
        if (token == null || token.isBlank()) {
            Logger.error(String.format(MUST_NOT_BE_EMPTY, TOKEN_ENV));
            System.exit(1);
        }

        props.setProperty("host", host);
        props.setProperty("token", token);
        //props.setProperty("pwd", pwd);

        return props;
    }

}

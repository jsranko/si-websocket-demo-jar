package de.sranko_informatik.siwebsocketdemojar;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.tn5250j.Session5250;
import org.tn5250j.SessionConfig;

import java.security.Provider;
import java.util.Properties;

public class WebSocketSession5250 {
    private WebSocketSession session;
    private Session5250 session5250;

    private Properties sesProps;
    private String configurationResource;
    private String sessionName;
    private SessionConfig useConfig;

    public WebSocketSession5250(WebSocketSession session) {
        this.session = session;

        configurationResource = "";
        sessionName = "IBM CECC";
        sesProps = new Properties();
        sesProps.setProperty("SESSION_SCREEN_SIZE", "1");
        sesProps.setProperty("SESSION_CODE_PAGE", "1141");
        sesProps.setProperty("SESSION_HOST", "129.40.95.25");
        sesProps.setProperty("SESSION_CONFIG_RESOURCE", "");
        sesProps.setProperty("SESSION_TN_ENHANCED", "1");
        sesProps.setProperty("SESSION_TERM_NAME", "IBM CECC");
        sesProps.setProperty("SESSION_TERM_NAME_SYSTEM", "1");
        sesProps.setProperty("SESSION_HOST_PORT", "23");
        sesProps.setProperty("SESSION_CONNECT_USER", "CECUSER");
        sesProps.setProperty("SESSION_CONNECT_PASSWORD", "D_-t0j4%0Ukf5wc");

        SessionConfig useConfig = new SessionConfig(configurationResource, sessionName);
        this.session5250 = new Session5250(sesProps, configurationResource, sessionName, useConfig);
        this.session5250.connect();
    }

    public Session5250 getSession5250() {
        return session5250;
    }

    public void setSession5250(Session5250 session5250) {
        this.session5250 = session5250;
    }
}

package de.sranko_informatik.siwebsocketdemojar;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.tn5250j.Session5250;
import org.tn5250j.SessionConfig;

@Component
public class Tn5250Handler extends TextWebSocketHandler {
    static private List<SessionConfig> configs;
    private static final Map<Long, WebSocketSession5250> SESSIONS = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        Long uid = (Long) session.getAttributes().get("uid");

        if (!SESSIONS.containsKey(uid)) {
            session.sendMessage(new TextMessage("Etwas ist schief gelaufen."));
        }

        String response = new String();
        Session5250 session5250 = SESSIONS.get(uid).getSession5250();

        switch (message.getPayload()){

            case "status":
                response = session5250.getAllocDeviceName();
                break;
            default:
                response = message.getPayload();
                break;
        }



        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long uid = (Long)session.getAttributes().get("uid");
        SESSIONS.put(uid, new WebSocketSession5250(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long uid = (Long)session.getAttributes().get("uid");
        SESSIONS.remove(uid);
        if (session.isOpen()) {
            session.close();
        }
    }
}

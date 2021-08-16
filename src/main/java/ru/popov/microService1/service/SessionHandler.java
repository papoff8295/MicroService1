package ru.popov.microService1.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.popov.microService1.cash.SessionCash;
import ru.popov.microService1.cash.TimeCash;

import java.util.Date;

@Component
public class SessionHandler implements WebSocketHandler {

    private  final TimeCash timeCash;
    private final SessionCash sessionCash;

    public SessionHandler(TimeCash cash, SessionCash sessionCash) {
        this.timeCash = cash;
        this.sessionCash = sessionCash;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected");
        //save to cash time start session
        timeCash.saveEventCash("start", new Date());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

        Date startDate = timeCash.getTimeCash().get("start");
        Date endDate = new Date();
        Date timeWork = new Date(endDate.getTime() - startDate.getTime());
        long time = timeWork.getTime()/1000;
        //view console all parameters with task
        System.out.println("Disconnected! " + "Время взаимодействия: " + time +
                "сек. " + "Кол-во сообщений: " + sessionCash.getSessionCash().get("sessionId"));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
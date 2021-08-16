package ru.popov.microService1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import ru.popov.microService1.cash.SessionCash;
import ru.popov.microService1.config.MS1Config;
import ru.popov.microService1.model.Message;
import ru.popov.microService1.service.MessageService;
import ru.popov.microService1.service.SessionHandler;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
public class MS2Controller {

    private final MS1Config ms1Config;
    private final SessionHandler sessionHandler;
    private WebSocketSession session;
    private final MessageService service;
    private final SessionCash sessionCash;

    public MS2Controller(MS1Config ms1Config, SessionHandler sessionHandler, MessageService service, SessionCash sessionCash) {
        this.ms1Config = ms1Config;
        this.sessionHandler = sessionHandler;
        this.service = service;
        this.sessionCash = sessionCash;
    }


    @GetMapping("/start")
    public void start() throws ExecutionException, InterruptedException, IOException {
        //set period action
        long period = ms1Config.getPeriod() * 1000;

        //time start of action
        Date date = new Date();

        Message message = null;

        long sessionId = 1;

        WebSocketClient client = new StandardWebSocketClient();
        //set connection
        session = client.doHandshake(sessionHandler, ms1Config.getMS2Url()).get();


        while ((new Date().getTime() - date.getTime()) < period) {
            message = new Message();
            message.setSessionId(sessionId);
            ++sessionId;
            message.setMC1Time(new Date());

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(message);

            try {
                session.sendMessage(new TextMessage(json));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //save to cash number of sessions
        sessionCash.saveEventCash("sessionId", sessionId);
    }

    @GetMapping("/stop")
    public void stop() throws IOException {
        if (session.isOpen()) {
            session.close();
        }
    }

    @PostMapping
    public void getFromMS3(@RequestBody Message message) {
        //receive message from ms3(ms2) and save
        message.setEndTime(new Date());
        service.save(message);
    }
}

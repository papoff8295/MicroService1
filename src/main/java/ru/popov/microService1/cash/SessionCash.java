package ru.popov.microService1.cash;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Setter
@Getter
public class SessionCash {
    private final Map<String, Long> sessionCash = new HashMap<>();

    public void saveEventCash(String key, Long id) {
        sessionCash.put(key, id);
    }
}

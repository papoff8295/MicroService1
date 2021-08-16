package ru.popov.microService1.cash;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Setter
@Getter
public class TimeCash {

    private final Map<String, Date> timeCash = new HashMap<>();

    public void saveEventCash(String key, Date date) {
        timeCash.put(key, date);
    }
}

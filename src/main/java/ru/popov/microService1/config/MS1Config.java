package ru.popov.microService1.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MS1Config {
    @Value("${MS2.url}")
    private String MS2Url;

    @Value("${timer.period}")
    private long period;
}

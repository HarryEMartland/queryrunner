package com.github.harryemartland.queryrunner.domain.argument.type;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeArgumentType implements ArgumentType<LocalDateTime> {
    @Override
    public LocalDateTime convert(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(string, formatter);
    }

    @Override
    public String htmlComponent() {
        return "<input class=\"form-control\" type=\"datetime-local\"/>";
    }
}

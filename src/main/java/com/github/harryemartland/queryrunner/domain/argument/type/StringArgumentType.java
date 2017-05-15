package com.github.harryemartland.queryrunner.domain.argument.type;

import org.springframework.stereotype.Component;

@Component
public class StringArgumentType implements ArgumentType<String> {
    @Override
    public String convert(String string) {
        return string;
    }

    @Override
    public String htmlComponent() {
        return "<input class=\"form-control\" type=\"text\"/>";
    }
}

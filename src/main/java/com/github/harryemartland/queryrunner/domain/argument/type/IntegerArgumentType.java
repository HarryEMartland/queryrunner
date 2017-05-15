package com.github.harryemartland.queryrunner.domain.argument.type;

import org.springframework.stereotype.Component;

@Component
public class IntegerArgumentType implements ArgumentType<Integer> {
    @Override
    public Integer convert(String string) {
        return Integer.parseInt(string);
    }

    @Override
    public String htmlComponent() {
        return "<input class=\"form-control\" type=\"number\"/>";
    }
}

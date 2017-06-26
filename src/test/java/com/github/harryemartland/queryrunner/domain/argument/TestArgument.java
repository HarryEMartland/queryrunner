package com.github.harryemartland.queryrunner.domain.argument;

import com.github.harryemartland.queryrunner.domain.argument.type.ArgumentType;

public class TestArgument implements Argument<Integer> {
    @Override
    public String getDisplayName() {
        return "test name";
    }

    @Override
    public ArgumentType getType() {
        return new ArgumentType() {
            @Override
            public Object convert(String string) {
                return Integer.parseInt(string);
            }

            @Override
            public String htmlComponent() {
                return "test html";
            }
        };
    }

    @Override
    public Integer getOrder() {
        return 100;
    }

    @Override
    public String getDefaultValue() {
        return "test default";
    }
}

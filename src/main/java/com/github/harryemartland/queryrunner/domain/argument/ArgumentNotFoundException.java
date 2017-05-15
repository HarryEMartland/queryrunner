package com.github.harryemartland.queryrunner.domain.argument;

public class ArgumentNotFoundException extends RuntimeException {

    public ArgumentNotFoundException(String argumentName) {
        super("Could not find argument: " + argumentName);
    }
}

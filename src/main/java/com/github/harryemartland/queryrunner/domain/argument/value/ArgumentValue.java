package com.github.harryemartland.queryrunner.domain.argument.value;

import com.github.harryemartland.queryrunner.domain.argument.Argument;
import com.github.harryemartland.queryrunner.domain.argument.type.ArgumentType;

public class ArgumentValue<T> {

    private Argument argument;

    private String argumentValue;

    public ArgumentValue(Argument argument, String argumentValue) {
        this.argument = argument;
        this.argumentValue = argumentValue;
    }

    public <T> ArgumentType<T> getArgumentType() {
        return argument.getType();
    }

    public Argument getArgument() {
        return argument;
    }

    public String getArgumentValue() {
        return argumentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArgumentValue<?> that = (ArgumentValue<?>) o;

        if (argument != null ? !argument.equals(that.argument) : that.argument != null) {
            return false;
        }
        return argumentValue != null
                ? argumentValue.equals(that.argumentValue) : that.argumentValue == null;
    }

    @Override
    public int hashCode() {
        int result = argument != null ? argument.hashCode() : 0;
        result = 31 * result + (argumentValue != null ? argumentValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArgumentValue{"
                + "argument=" + argument
                + ", argumentValue='" + argumentValue + '\''
                + '}';
    }
}

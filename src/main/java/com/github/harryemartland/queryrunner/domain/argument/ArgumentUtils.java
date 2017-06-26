package com.github.harryemartland.queryrunner.domain.argument;

import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import java.util.List;

public class ArgumentUtils {

    private ArgumentUtils() {
        //hide constructor
    }

    /** Returns the typed value for an argument.
     *
     * @param argumentValues the list of all argument values
     * @param argumentValueClass the class of the required argument
     * @param <T> the type of the argument
     * @return the typed value of the argument
     */
    public static <T> T getArgumentValue(List<ArgumentValue> argumentValues,
                                         Class<? extends Argument<T>> argumentValueClass) {
        return argumentValues.stream()
                .filter(argumentValue ->
                        argumentValue.getArgument().getClass().equals(argumentValueClass))
                .findFirst()
                .map(argumentValue -> ArgumentUtils.mapToValue((ArgumentValue<T>) argumentValue))
                .orElseThrow(() ->
                        new ArgumentNotFoundException(argumentValueClass.getCanonicalName()));
    }

    private static <T> T mapToValue(ArgumentValue<T> argumentValue) {
        return argumentValue.<T>getArgumentType().<T>convert(argumentValue.getArgumentValue());
    }

}

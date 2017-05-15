package com.github.harryemartland.queryrunner.domain.argument;

import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import java.util.List;

public class ArgumentUtils {

    private ArgumentUtils() {
        //hide constructor
    }

    public static  <T> T getArgumentValue(List<ArgumentValue> argumentValues, Class<? extends Argument<T>> argumentValueClass){
        return argumentValues.stream()
                .filter(argumentValue -> argumentValue.getArgument().getClass().equals(argumentValueClass))
                .findFirst()
                .map(argumentValue -> (T)argumentValue.getArgumentType().convert(argumentValue.getArgumentValue()))
                .orElseThrow(()->new ArgumentNotFoundException(argumentValueClass.getCanonicalName()));
    }
}

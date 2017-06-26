package com.github.harryemartland.queryrunner.domain.argument;

import com.github.harryemartland.queryrunner.domain.argument.type.ArgumentType;
import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class ArgumentUtilsTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldReturnValue() {

        List<ArgumentValue> arguments = Arrays.asList(
                createArgumentValue(Mockito.mock(Argument.class)),
                createArgumentValue(new TestArgument()),
                createArgumentValue(Mockito.mock(Argument.class))
        );

        Integer argumentValue = ArgumentUtils.getArgumentValue(arguments, TestArgument.class);
        Assert.assertEquals((Integer) 5, argumentValue);
    }

    @Test()
    public void shouldThrowArgumentNotFoundExceptionWhenArgumentDoesNotExist() {
        exception.expect(ArgumentNotFoundException.class);
        exception.expectMessage("Could not find argument: "
                + TestArgument.class.getCanonicalName());
        List<ArgumentValue> arguments = Arrays.asList(
                createArgumentValue(Mockito.mock(Argument.class)),
                createArgumentValue(Mockito.mock(Argument.class))
        );

        ArgumentUtils.getArgumentValue(arguments, TestArgument.class);
    }

    private ArgumentValue createArgumentValue(Argument argument) {
        ArgumentValue mock = Mockito.mock(ArgumentValue.class);
        Mockito.when(mock.getArgument()).thenReturn(argument);
        ArgumentType type = argument.getType();
        Mockito.when(mock.getArgumentType()).thenReturn(type);
        Mockito.when(mock.getArgumentValue()).thenReturn("5");
        return mock;
    }
}
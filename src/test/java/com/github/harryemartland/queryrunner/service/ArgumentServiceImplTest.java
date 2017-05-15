package com.github.harryemartland.queryrunner.service;

import com.github.harryemartland.queryrunner.domain.argument.Argument;
import com.github.harryemartland.queryrunner.domain.argument.ArgumentNotFoundException;
import com.github.harryemartland.queryrunner.domain.argument.TestArgument;
import com.github.harryemartland.queryrunner.domain.argument.type.ArgumentType;
import com.github.harryemartland.queryrunner.domain.argument.type.IntegerArgumentType;
import com.github.harryemartland.queryrunner.dto.ArgumentDTO;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentServiceImplTest {

    @Mock
    private Argument argument1;

    private Argument argument2 = new TestArgument();

    @Mock
    private Argument argument3;

    @InjectMocks
    private ArgumentService argumentService = new ArgumentServiceImpl();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(argumentService, "arguments", Arrays.asList(argument1, argument2, argument3));
        Mockito.when(argument1.getType()).thenReturn((ArgumentType) new IntegerArgumentType());
        Mockito.when(argument3.getType()).thenReturn((ArgumentType) new IntegerArgumentType());
    }

    @Test
    public void shouldFindArgument() {
        Argument foundArgument = argumentService.findArgument(argument2.getClass().getCanonicalName());

        Assert.assertEquals(argument2, foundArgument);
    }

    @Test
    public void shouldConvertToDTOs() {
        List<ArgumentDTO> argumentDTOList = argumentService.toDTOs();
        ArgumentDTO secondArgument = argumentDTOList.get(1);
        Assert.assertEquals(TestArgument.class.getCanonicalName(), secondArgument.getName());
        Assert.assertEquals("test name", secondArgument.getDisplayName());
        Assert.assertEquals("test html", secondArgument.getHtmlElement());
    }

    @Test()
    public void shouldThrowArgumentNotFoundExceptionWhenArgumentDoesNotExist() {
        exception.expect(ArgumentNotFoundException.class);
        exception.expectMessage("Could not find argument: not.a.real.argument");
        argumentService.findArgument("not.a.real.argument");
    }
}
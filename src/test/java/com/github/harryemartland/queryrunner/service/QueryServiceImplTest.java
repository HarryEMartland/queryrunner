package com.github.harryemartland.queryrunner.service;

import com.github.harryemartland.queryrunner.domain.argument.type.ArgumentType;
import com.github.harryemartland.queryrunner.domain.argument.type.StringArgumentType;
import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import com.github.harryemartland.queryrunner.domain.query.NullQueryResultException;
import com.github.harryemartland.queryrunner.dto.ArgumentValueDTO;
import com.github.harryemartland.queryrunner.domain.argument.Argument;
import com.github.harryemartland.queryrunner.domain.query.Query;
import com.github.harryemartland.queryrunner.domain.query.QueryNotFoundException;
import com.github.harryemartland.queryrunner.domain.query.QueryResult;
import com.github.harryemartland.queryrunner.dto.QueryDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class QueryServiceImplTest {

    @Mock
    private Query query1;

    private Query query2 = new TestQuery();

    @Mock
    private Query query3;

    private Query query4 = new NullResultQuery();

    @Mock
    private TestArgument pickUpDepotIdArgument;

    @Mock
    private ArgumentService argumentService;

    @InjectMocks
    private QueryService queryService = new QueryServiceImpl();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(queryService, "queries", Arrays.asList(query1, query2, query3, query4));
        Mockito.when(query1.getResult(Mockito.anyList())).thenReturn(QueryResult.FAIL);
        Mockito.when(argumentService.findArgument(Mockito.anyString())).thenReturn(pickUpDepotIdArgument);
    }

    @Test
    public void shouldCallQueyMethodOnCorrectQuery() {
        List<ArgumentValueDTO> argumentValueDTOList = new ArrayList<>();
        QueryResult result = queryService.requestQuery(argumentValueDTOList, TestQuery.class.getCanonicalName());
        Assert.assertEquals(QueryResult.OK, result);
    }

    @Test
    public void shouldMapArguments() {
        List<ArgumentValueDTO> argumentValueDTOList = Collections.singletonList(createArgumentValueDTO("testName", "testValue"));
        queryService.requestQuery(argumentValueDTOList, query1.getClass().getCanonicalName());
        Mockito.verify(query1).getResult(Collections.singletonList(new ArgumentValue(pickUpDepotIdArgument, "testValue")));
    }

    @Test
    public void shouldConverToDTOs() {
        List<QueryDTO> queryDTOS = queryService.toDTOs();
        QueryDTO secondQuery = queryDTOS.get(1);
        Assert.assertEquals("query 1", secondQuery.getDisplayName());
        Assert.assertEquals(query2.getClass().getCanonicalName(), secondQuery.getName());
        Assert.assertEquals(Collections.singletonList(TestArgument.class.getCanonicalName()), secondQuery.getDependencies());
    }

    @Test()
    public void shouldThrowExceptionWhenQueryNotFound() {
        exception.expect(QueryNotFoundException.class);
        exception.expectMessage("Could not find query: not.a.real.class");

        queryService.requestQuery(Collections.emptyList(), "not.a.real.class");
    }

    @Test(expected = NullQueryResultException.class)
    public void shouldThrowNullQueryResultWhenQueryReturnsNull() {
        queryService.requestQuery(Collections.emptyList(), query4.getClass().getCanonicalName());
    }

    private ArgumentValueDTO createArgumentValueDTO(String name, String value){
        ArgumentValueDTO mock = Mockito.mock(ArgumentValueDTO.class);
        Mockito.when(mock.getName()).thenReturn(name);
        Mockito.when(mock.getValue()).thenReturn(value);
        return mock;
    }

    private class NullResultQuery implements Query{
        @Override
        public List<? extends Argument> getDependencies() {
            return Collections.singletonList(new TestArgument());
        }

        @Override
        public QueryResult getResult(List<ArgumentValue> argumentValues) {
            return null;
        }

        @Override
        public String getDisplayName() {
            return "query 1";
        }
    }

    private class TestQuery implements Query{
        @Override
        public List<? extends Argument> getDependencies() {
            return Collections.singletonList(new TestArgument());
        }

        @Override
        public QueryResult getResult(List<ArgumentValue> argumentValues) {
            return QueryResult.OK;
        }

        @Override
        public String getDisplayName() {
            return "query 1";
        }
    }

    private class TestArgument implements Argument<String>{
        @Override
        public String getDisplayName() {
            return "test argument";
        }

        @Override
        public ArgumentType<String> getType() {
            return new StringArgumentType();
        }

        @Override
        public Integer getOrder() {
            return 100;
        }
    }
}
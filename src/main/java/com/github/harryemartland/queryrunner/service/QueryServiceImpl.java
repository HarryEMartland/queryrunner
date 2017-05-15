package com.github.harryemartland.queryrunner.service;

import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import com.github.harryemartland.queryrunner.domain.query.NullQueryResultException;
import com.github.harryemartland.queryrunner.domain.query.Query;
import com.github.harryemartland.queryrunner.domain.query.QueryNotFoundException;
import com.github.harryemartland.queryrunner.domain.query.QueryResult;
import com.github.harryemartland.queryrunner.dto.ArgumentValueDTO;
import com.github.harryemartland.queryrunner.dto.QueryDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private List<Query> queries;

    @Autowired
    private ArgumentService argumentService;

    @Override
    public QueryResult requestQuery(List<ArgumentValueDTO> arguments, String queryName) {
         Query query = queries.stream().filter(query1 -> query1.getClass().getCanonicalName().equalsIgnoreCase(queryName))
                .findFirst().orElseThrow(()->new QueryNotFoundException(queryName));

        QueryResult result = query.getResult(convertArguments(arguments));
        if (result == null){
            throw new NullQueryResultException();
        }
        return result;
    }

    @Override
    public List<QueryDTO> toDTOs() {
        return queries.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private QueryDTO toDTO(Query query) {
        return new QueryDTO(
                query.getClass().getCanonicalName(),
                query.getDisplayName(),
                query.getDependencies().stream().map(argument->argument.getClass().getCanonicalName()).collect(Collectors.toList())
        );
    }

    private List<ArgumentValue> convertArguments(List<ArgumentValueDTO> arguments) {
        return arguments.stream().map(this::convertArgument).collect(Collectors.toList());
    }

    private ArgumentValue convertArgument(ArgumentValueDTO argumentValueDTO) {
        return new ArgumentValue(argumentService.findArgument(argumentValueDTO.getName()), argumentValueDTO.getValue());
    }

}

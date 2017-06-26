package com.github.harryemartland.queryrunner.service;

import com.github.harryemartland.queryrunner.domain.query.QueryResult;
import com.github.harryemartland.queryrunner.dto.ArgumentValueDTO;
import com.github.harryemartland.queryrunner.dto.QueryDTO;
import java.util.List;

public interface QueryService {
    QueryResult requestQuery(List<ArgumentValueDTO> arguments, String query);

    List<QueryDTO> toDTOs();
}

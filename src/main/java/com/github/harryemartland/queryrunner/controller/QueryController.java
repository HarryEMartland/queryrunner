package com.github.harryemartland.queryrunner.controller;

import com.github.harryemartland.queryrunner.domain.query.QueryResult;
import com.github.harryemartland.queryrunner.dto.ArgumentValueDTO;
import com.github.harryemartland.queryrunner.dto.QueryDTO;
import com.github.harryemartland.queryrunner.service.QueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping
    public List<QueryDTO> getQuerys(){
        return queryService.toDTOs();
    }

    @PostMapping("/{query:.+}")
    public QueryResult requestQuery(@RequestBody List<ArgumentValueDTO> arguments, @PathVariable("query") String query){
        return queryService.requestQuery(arguments, query);
    }
}

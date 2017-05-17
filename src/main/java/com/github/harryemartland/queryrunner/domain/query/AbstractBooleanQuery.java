package com.github.harryemartland.queryrunner.domain.query;

import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractBooleanQuery implements Query {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBooleanQuery.class);

    @Override
    public QueryResult getResult(List<ArgumentValue> argumentValues) {
        try {
            return runQueryAndReturnResult(argumentValues);
        } catch (EmptyResultDataAccessException e) {
            LOG.trace("Result not found: {}", this.getClass().getSimpleName(), e);
            return QueryResult.NOT_FOUND;
        }
    }

    private QueryResult runQueryAndReturnResult(List<ArgumentValue> argumentValues) {
        if (runQuery(argumentValues)) {
            return QueryResult.OK;
        } else {
            return QueryResult.FAIL;
        }
    }

    private Boolean runQuery(List<ArgumentValue> argumentValues) {
        return getJdbcTemplate().queryForObject(getQuery(), getArgs(argumentValues), Boolean.class);
    }

    protected abstract Object[] getArgs(List<ArgumentValue> argumentValues);

    protected abstract JdbcTemplate getJdbcTemplate();

    protected abstract String getQuery();

}

package com.github.harryemartland.queryrunner.domain.query;

public class QueryNotFoundException extends RuntimeException {
    public QueryNotFoundException(String queryName) {
        super("Could not find query: " + queryName);
    }
}

package com.github.harryemartland.queryrunner.dto;

import java.util.List;

public class QueryDTO {

    private String name;
    private String displayName;
    private List<String> dependencies;
    private List<String> optionalDependencies;

    /** Domain Transfere Object for a query.
     *
     * @param name class name of query
     * @param displayName display name of query
     * @param dependencies list of required dependencies for query
     * @param optionalDependencies list of optional dependencies for query
     */
    public QueryDTO(String name, String displayName,
                    List<String> dependencies, List<String> optionalDependencies) {
        this.name = name;
        this.displayName = displayName;
        this.dependencies = dependencies;
        this.optionalDependencies = optionalDependencies;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public List<String> getOptionalDependencies() {
        return optionalDependencies;
    }
}

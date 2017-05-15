package com.github.harryemartland.queryrunner.dto;

import java.util.List;

public class QueryDTO {

    private String name;
    private String displayName;
    private List<String> dependencies;

    public QueryDTO(String name, String displayName, List<String> dependencies) {
        this.name = name;
        this.displayName = displayName;
        this.dependencies = dependencies;
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
}

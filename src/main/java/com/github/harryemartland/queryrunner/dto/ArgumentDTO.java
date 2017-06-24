package com.github.harryemartland.queryrunner.dto;

public class ArgumentDTO {

    private String name;
    private String displayName;
    private String htmlElement;
    private Integer order;
    private String defaultValue;

    public ArgumentDTO(String name, String displayName, String htmlElement, Integer order, String defaultValue) {
        this.name = name;
        this.displayName = displayName;
        this.htmlElement = htmlElement;
        this.order = order;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHtmlElement() {
        return htmlElement;
    }

    public Integer getOrder() {
        return order;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}

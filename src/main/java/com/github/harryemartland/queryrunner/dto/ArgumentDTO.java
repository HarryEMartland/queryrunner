package com.github.harryemartland.queryrunner.dto;

public class ArgumentDTO {

    private String name;
    private String displayName;
    private String htmlElement;
    private Integer order;

    public ArgumentDTO(String name, String displayName, String htmlElement, Integer order) {
        this.name = name;
        this.displayName = displayName;
        this.htmlElement = htmlElement;
        this.order = order;
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
}

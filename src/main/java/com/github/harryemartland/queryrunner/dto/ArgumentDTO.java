package com.github.harryemartland.queryrunner.dto;

public class ArgumentDTO {

    private String name;
    private String displayName;
    private String htmlElement;

    public ArgumentDTO(String name, String displayName, String htmlElement) {
        this.name = name;
        this.displayName = displayName;
        this.htmlElement = htmlElement;
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
}

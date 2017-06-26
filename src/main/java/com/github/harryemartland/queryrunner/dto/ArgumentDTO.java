package com.github.harryemartland.queryrunner.dto;

public class ArgumentDTO {

    private String name;
    private String displayName;
    private String htmlElement;
    private Integer order;
    private String defaultValue;

    /** Domain Transfer Object for a query argument
     *
     * @param name the class name of the argument
     * @param displayName the display name of the argument
     * @param htmlElement the html for the ui component e.g. text box
     * @param order the value to compare to for the order of arguments
     * @param defaultValue the default value of the ui component
     */
    public ArgumentDTO(String name, String displayName, String htmlElement,
                       Integer order, String defaultValue) {
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

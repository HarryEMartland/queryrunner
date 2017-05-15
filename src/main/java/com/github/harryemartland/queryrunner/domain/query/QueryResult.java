package com.github.harryemartland.queryrunner.domain.query;

public class QueryResult {

    public static final QueryResult OK = new QueryResult("<span class=\"glyphicon glyphicon-ok\" style=\"color:green\"></span>", "success");
    public static final QueryResult FAIL = new QueryResult("<span class=\"glyphicon glyphicon-remove icon-warning\" style=\"color:red\"></span>", "error");
    public static final QueryResult NOT_FOUND = new QueryResult("<span class=\"glyphicon glyphicon-remove glyphicon-minus-sign\" style=\"color:red\"></span>", "error");

    private String displayValue;
    private String value;

    private QueryResult(String displayValue, String value) {
        this.displayValue = displayValue;
        this.value = value;
    }

    public String getDisplayValue(){
        return displayValue;
    }

    public String getValue() {
        return value;
    }
}

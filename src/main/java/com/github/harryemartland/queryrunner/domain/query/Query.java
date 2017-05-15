package com.github.harryemartland.queryrunner.domain.query;

import com.github.harryemartland.queryrunner.domain.argument.Argument;
import com.github.harryemartland.queryrunner.domain.argument.value.ArgumentValue;
import java.util.List;

public interface Query {

    List<? extends Argument> getDependencies();

    QueryResult getResult(List<ArgumentValue> argumentValues);

    String getDisplayName();

}

package com.github.harryemartland.queryrunner.domain.argument;

import com.github.harryemartland.queryrunner.domain.argument.type.ArgumentType;

public interface Argument<T> {

    String getDisplayName();

    ArgumentType<T> getType();

    Integer getOrder();

}

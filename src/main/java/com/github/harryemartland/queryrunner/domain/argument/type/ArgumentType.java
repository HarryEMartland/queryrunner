package com.github.harryemartland.queryrunner.domain.argument.type;

public interface ArgumentType<T> {

    T convert(String string);

    String htmlComponent();

}

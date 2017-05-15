package com.github.harryemartland.queryrunner.service;

import com.github.harryemartland.queryrunner.domain.argument.Argument;
import com.github.harryemartland.queryrunner.dto.ArgumentDTO;
import java.util.List;

public interface ArgumentService {

    public Argument findArgument(String name);

    List<ArgumentDTO> toDTOs();
}

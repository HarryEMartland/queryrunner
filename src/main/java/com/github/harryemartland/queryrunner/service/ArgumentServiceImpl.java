package com.github.harryemartland.queryrunner.service;

import com.github.harryemartland.queryrunner.domain.argument.Argument;
import com.github.harryemartland.queryrunner.domain.argument.ArgumentNotFoundException;
import com.github.harryemartland.queryrunner.dto.ArgumentDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArgumentServiceImpl implements ArgumentService {

    @Autowired
    private List<Argument> arguments;

    @Override
    public Argument findArgument(String name) {
        return arguments.stream()
                .filter(argument -> argument.getClass().getCanonicalName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ArgumentNotFoundException(name));
    }

    @Override
    public List<ArgumentDTO> toDTOs() {
        return arguments.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ArgumentDTO toDTO(Argument argument) {
        return new ArgumentDTO(
                argument.getClass().getCanonicalName(),
                argument.getDisplayName(),
                argument.getType().htmlComponent(),
                argument.getOrder());
    }

}

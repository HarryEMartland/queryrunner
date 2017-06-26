package com.github.harryemartland.queryrunner.controller;

import com.github.harryemartland.queryrunner.dto.ArgumentDTO;
import com.github.harryemartland.queryrunner.service.ArgumentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("argument")
public class ArgumentController {

    @Autowired
    private ArgumentService argumentService;

    @GetMapping
    public List<ArgumentDTO> arguments() {
        return argumentService.toDTOs();
    }

}

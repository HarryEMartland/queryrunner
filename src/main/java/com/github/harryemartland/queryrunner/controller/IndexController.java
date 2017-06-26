package com.github.harryemartland.queryrunner.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Value("${queryrunner.title}")
    private String title;

    @Value("${queryrunner.subtitle}")
    private String subtitle;

    /** Index mapping.
     *
     * @param model injected modle to set properties on
     * @return the name of the template to render
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", title);
        model.addAttribute("subtitle", subtitle);
        return "index";
    }
}

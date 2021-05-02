package xyz.cychen.stumanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @RequestMapping("/add")
    public String getAddPage() {
        return "add";
    }

    @RequestMapping("/edit/{realId}")
    public String getEditPage(@PathVariable("realId") Integer realId, Model model) {
        model.addAttribute("realId", realId);
        return "edit";
    }
}

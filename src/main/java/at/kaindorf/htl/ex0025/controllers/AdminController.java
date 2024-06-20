package at.kaindorf.htl.ex0025.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public String adminDashboard()
    {
        logger.info("--> adminDashboard() !!");
        return "adminDashboard";
    }
}

package at.kaindorf.htl.ex0025.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("login")
    public String loginForm()
    {
        logger.info("--> loginForm() !!");
        return "login";
    }

    @GetMapping( {"/", "index" } )
    public String homePage()
    {
        logger.info("--> homePage() !!");
        return "index";
    }
}

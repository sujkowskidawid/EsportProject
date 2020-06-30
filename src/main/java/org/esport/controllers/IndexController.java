package org.esport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexGet() {
        return "index";
    }

    @GetMapping("/nolink")
    public String noLinkGet(){return "nolink";}

    @GetMapping("/nostream")
    public String noStreamGet(){return "nostream";}
}

package org.esport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ChooseController {

    @GetMapping("/choose")
    public String chooseGame() {
        return "choose";
    }}


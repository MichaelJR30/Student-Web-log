package com.example.advancedjavaproject;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    logRepository log;
    @Autowired
    public WebController(logRepository log) {
        this.log = log;
    }

    @GetMapping("/")
    public String studentSignIn(Model model) {
        model.addAttribute("signInForm", new StudentLog());
        return "StudentLogIn";
    }

    @PostMapping("/Success")
    public String successfulSignIn(@Valid @ModelAttribute("signInForm") StudentLog stud, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "StudentLogIn";
        }
        log.save(stud);
        model.addAttribute("signInForm", stud);
        return "SignInSuccessful";
    }
}

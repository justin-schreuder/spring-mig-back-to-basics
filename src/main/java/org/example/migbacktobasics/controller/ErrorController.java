package org.example.migbacktobasics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @GetMapping(params = "error=math")
    public String error1() {
        throw new ArithmeticException("This math is invalid!");
    }

    @GetMapping(params = "error=index")
    public String error2() {
        throw new IndexOutOfBoundsException("You are out of bounds!");
    }

}

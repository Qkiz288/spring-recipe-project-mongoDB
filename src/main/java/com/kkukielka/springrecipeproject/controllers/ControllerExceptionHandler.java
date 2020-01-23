package com.kkukielka.springrecipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    // temporarily commented out
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView numberFormatHandler(Exception exception) {
//
//        log.error("Handling number format error");
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("400error");
//        modelAndView.addObject("exception", exception);
//
//        return modelAndView;
//
//    }

}

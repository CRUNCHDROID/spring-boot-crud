package net.crunchdroid.controllers;

import net.crunchdroid.exceptions.ArticleNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author CrunchDroid
 */
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = {NumberFormatException.class, ArticleNotFoundException.class})
    public String error404() {
        return "error/404";
    }

}

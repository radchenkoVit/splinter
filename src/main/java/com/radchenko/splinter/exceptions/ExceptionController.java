package com.radchenko.splinter.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    private static final String ERROR_MESSAGE_ATTR = "errorMessage";

//    @ExceptionHandler(value = Exception.class)TODO
//    public ModelAndView common(Exception ex) {
//        return prepareModelAndView(ERROR_PAGE, ERROR_MESSAGE_ATTR, ex);
//    }
//
//    @ExceptionHandler(value = NoHandlerFoundException.class)
//    public ModelAndView handle(NoHandlerFoundException ex) {
//        return prepareModelAndView(NOT_FOUND_PAGE, ERROR_MESSAGE_ATTR, ex);
//    }

    @ExceptionHandler(value = ActivationCodeNotFoundException.class)
    public ModelAndView activationHandler(ActivationCodeNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModel().put("message", "Activation code is broken");
        logger.warn("Exception happen, exception: {}", e);
        return modelAndView;
    }
}

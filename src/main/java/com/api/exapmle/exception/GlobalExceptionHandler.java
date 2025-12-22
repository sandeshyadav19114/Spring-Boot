package com.api.exapmle.exception;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {



    //Exception Handler Method acts like catch block

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> ResourceNotFound(
            ResourceNotFound r,
            WebRequest request
    ){

        ErrorDetails errorDetails = new ErrorDetails(r.getMessage(), new Date(), request.getDescription(false));
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

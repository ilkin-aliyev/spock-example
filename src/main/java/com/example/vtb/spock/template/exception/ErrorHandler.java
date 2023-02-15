package com.example.vtb.spock.template.exception;

import com.example.vtb.spock.template.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.vtb.spock.template.model.constants.ErrorMessage.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorDto handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ErrorDto(UNEXPECTED_EXCEPTION);
    }
}

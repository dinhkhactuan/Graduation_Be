package com.Graduation_Be.exception;

import com.Graduation_Be.exception.exceptionOption.BadGatewayException;
import com.Graduation_Be.exception.exceptionOption.BadRequestException;
import com.Graduation_Be.exception.exceptionOption.InternalServerException;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorCode> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorCode error = new ErrorCode(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorCode> handleBadRequestException(BadRequestException ex) {
        ErrorCode err = new ErrorCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorCode> handleInternalServer(InternalServerException ex) {
        ErrorCode err = new ErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<ErrorCode> handleBadGateway(BadGatewayException ex) {
        ErrorCode err = new ErrorCode(HttpStatus.BAD_GATEWAY.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorCode> handleRecordNotFound(EmptyResultDataAccessException ex) {
        ErrorCode error = new ErrorCode(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
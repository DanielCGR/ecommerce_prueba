package com.upc.ecommerce.handler;

import com.upc.ecommerce.exception.IncorrectOrderRequestException;
import com.upc.ecommerce.exception.OrderNotFoundException;
import com.upc.ecommerce.exception.OrderServiceExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.sql.rowset.WebRowSet;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class OrderServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions (Exception exception, WebRequest request){
        OrderServiceExceptionResponse response = new OrderServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(IncorrectOrderRequestException.class)
    public ResponseEntity<Object> handleIncorrectRequest (IncorrectOrderRequestException exception, WebRequest request){
        OrderServiceExceptionResponse response = new OrderServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException (OrderNotFoundException exception, WebRequest request){
        OrderServiceExceptionResponse response = new OrderServiceExceptionResponse(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }

}

package ru.gb.wintermarket.carts.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gb.wintermarket.api.exceptions.AppError;
import ru.gb.wintermarket.api.exceptions.FieldsValidatorError;
import ru.gb.wintermarket.api.exceptions.ResourceNotFoundException;
import ru.gb.wintermarket.api.exceptions.ValidationException;

//Делается, что бы приложение не падало при ошибке
@ControllerAdvice//Данную логику распространяем на все контроллеры
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value()
                ,e.getMessage())
                ,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<FieldsValidatorError> catchValidatorException(ValidationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new FieldsValidatorError(e.getErrorFieldMessages())
                ,HttpStatus.BAD_REQUEST);
    }

}


package org.mitenkov.coursemanager.exception;

import org.mitenkov.coursemanager.dto.ErrorMessageDto;
import org.mitenkov.coursemanager.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ErrorCodeException.class)
    public ResponseEntity<ErrorMessageDto> errorCodeException(ErrorCodeException ex) {
        ErrorCode error = ex.getErrorCode();
        return new ResponseEntity<>(new ErrorMessageDto(error), error.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessageDto> httpException() {
        ErrorMessageDto dto = new ErrorMessageDto(ErrorCode.CANNOT_PARSE_HTTP);
        return new ResponseEntity<>(dto, dto.errorCode().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> exception() {
        ErrorMessageDto dto = new ErrorMessageDto(ErrorCode.UNKNOWN_ERROR);
        return new ResponseEntity<>(dto, dto.errorCode().getHttpStatus());
    }
}

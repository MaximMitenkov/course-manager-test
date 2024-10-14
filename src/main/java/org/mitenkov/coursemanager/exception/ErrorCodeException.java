package org.mitenkov.coursemanager.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.enums.ErrorCode;

@Getter
@RequiredArgsConstructor
public class ErrorCodeException extends RuntimeException {
    private final ErrorCode errorCode;
}

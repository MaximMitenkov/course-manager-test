package org.mitenkov.coursemanager.dto;

import org.mitenkov.coursemanager.enums.ErrorCode;

public record ErrorMessageDto(String message, ErrorCode errorCode) {

    public ErrorMessageDto(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode);
    }
}

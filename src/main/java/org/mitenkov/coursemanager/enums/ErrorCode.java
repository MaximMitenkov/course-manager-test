package org.mitenkov.coursemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ILLEGAL_TIME("Запрос был отправлен не в установленное время", HttpStatus.BAD_REQUEST),
    COURSE_IS_FULL("На курсе не осталось свободных мест", HttpStatus.BAD_REQUEST),
    CANNOT_PARSE_HTTP("Ошибка десериализации HTTP", HttpStatus.BAD_REQUEST),
    UNKNOWN_ERROR("Неизвестная ошибка", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_SUCH_ELEMENT("Требуемый элемент не был найден", HttpStatus.NOT_FOUND),
    DUPLICATES_NOT_ALLOWED("Сущность с такими параметрами уже существует", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;
}

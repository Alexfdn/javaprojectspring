package ru.alexfed.MySecondTestAppSpringBoot.model;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Response {
    // Уникальный идентификатор сообщение
    private String uid;
    // Имя системы отправителя
    private String operationUid;
    // Время создания сообщения
    private String systemTime;
    // код
    private Codes code;
    private Double annualBonus;
    // Время создания сообщения
    private ErrorCodes errorCode;
    // Сообщение об ошибке
    private ErrorMessages errorMessage;
}


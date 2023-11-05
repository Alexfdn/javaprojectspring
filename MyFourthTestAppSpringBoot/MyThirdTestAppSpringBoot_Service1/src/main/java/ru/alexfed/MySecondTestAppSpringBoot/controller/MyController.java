package ru.alexfed.MySecondTestAppSpringBoot.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alexfed.MySecondTestAppSpringBoot.model.*;
import ru.alexfed.MySecondTestAppSpringBoot.service.ValidationService;
import ru.alexfed.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.alexfed.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.alexfed.MySecondTestAppSpringBoot.service.UnsupportedCodeValidationService;
import ru.alexfed.MySecondTestAppSpringBoot.util.DateTimeUtil;
import org.springframework.validation.BindingResult;
import ru.alexfed.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.alexfed.MySecondTestAppSpringBoot.service.ModifyRequestService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final UnsupportedCodeValidationService unsupportedCodeValidationService;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedCodeValidationService unsupportedCodeValidationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.unsupportedCodeValidationService = unsupportedCodeValidationService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Получен запрос обратной связи: {}", request);

        long startTime = System.currentTimeMillis();

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            log.error("Ошибка валидации: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Произошла неизвестная ошибка: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            unsupportedCodeValidationService.isValid(Integer.parseInt(request.getUid()));
        } catch (UnsupportedCodeException e) {
            log.error("error: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.info("response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        modifyResponseService.modify(response);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("Время выполнения ms: {}", executionTime);
        log.info("response: {}", response);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}

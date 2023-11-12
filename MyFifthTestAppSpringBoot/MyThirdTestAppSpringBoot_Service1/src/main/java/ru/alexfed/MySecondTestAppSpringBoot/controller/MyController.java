//package ru.alexfed.MySecondTestAppSpringBoot.controller;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import ru.alexfed.MySecondTestAppSpringBoot.model.*;
//import ru.alexfed.MySecondTestAppSpringBoot.service.ValidationService;
//import ru.alexfed.MySecondTestAppSpringBoot.exception.ValidationFailedException;
//import ru.alexfed.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
//import ru.alexfed.MySecondTestAppSpringBoot.service.UnsupportedCodeValidationService;
//import ru.alexfed.MySecondTestAppSpringBoot.util.DateTimeUtil;
//import org.springframework.validation.BindingResult;
//import ru.alexfed.MySecondTestAppSpringBoot.service.ModifyResponseService;
//import ru.alexfed.MySecondTestAppSpringBoot.service.ModifyRequestService;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Autowired;
//import javax.validation.Valid;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Objects;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//public class MyController {
//    private final ValidationService validationService;
//    private final ModifyResponseService modifyResponseService;
//    private final UnsupportedCodeValidationService unsupportedCodeValidationService;
//
//    private final ModifyRequestService modifyRequestService;
//    @Autowired
//
//    public MyController(ValidationService validationService, UnsupportedCodeValidationService unsupportedCodeValidationService,
//                        @Qualifier("ModifySystemTimeResponseService")
//                        ModifyResponseService modifyResponseService,
//                        ModifyRequestService modifyRequestService) {
//        this.validationService = validationService;
//        this.unsupportedCodeValidationService = unsupportedCodeValidationService;
//        this.modifyResponseService = modifyResponseService;
//        this.modifyRequestService = modifyRequestService;
//    }
//
//    @PostMapping(value = "/feedback")
//    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//
//        log.info("request: {}", request);
//
//        Response response = Response.builder()
//                .uid(request.getUid())
//                .operationUid(request.getOperationUid())
//                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
//                .code(Codes.SUCCESS)
//                .errorCode(ErrorCodes.EMPTY)
//                .errorMessage(ErrorMessages.EMPTY)
//                .build();
//
//        try {
//            validationService.isValid(bindingResult);
//        } catch (ValidationFailedException e) {
//            log.error("error: {}", e.getMessage());
//            response.setCode(Codes.FAILED);
//            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
//            response.setErrorMessage(ErrorMessages.VALIDATION);
//            log.info("response: {}", response);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            log.error("error: {}", e.getMessage());
//            response.setCode(Codes.FAILED);
//            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
//            response.setErrorMessage(ErrorMessages.UNKNOWN);
//            log.info("response: {}", response);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try {
//            unsupportedCodeValidationService.isValid(Integer.parseInt(request.getUid()));
//        } catch (UnsupportedCodeException e) {
//            log.error("error: {}", e.getMessage());
//            response.setCode(Codes.FAILED);
//            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
//            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
//            log.info("response: {}", response);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//        modifyResponseService.modify(response);
//        modifyRequestService.modify(request);
//
//        log.info("response: {}", response);
//        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
//    }
//}
package ru.alexfed.MySecondTestAppSpringBoot.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alexfed.MySecondTestAppSpringBoot.model.*;
import ru.alexfed.MySecondTestAppSpringBoot.service.*;
import ru.alexfed.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.alexfed.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.alexfed.MySecondTestAppSpringBoot.util.DateTimeUtil;
import org.springframework.validation.BindingResult;
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
    private final AnnualBonusServiceImpl annualBonusService;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedCodeValidationService unsupportedCodeValidationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService, AnnualBonusServiceImpl annualBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.unsupportedCodeValidationService = unsupportedCodeValidationService;
        this.annualBonusService = annualBonusService;
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
        Double annualBonus = annualBonusService.calculate(request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays());

        log.info("Время выполнения ms: {}", executionTime);
        log.info("response: {}", response);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}
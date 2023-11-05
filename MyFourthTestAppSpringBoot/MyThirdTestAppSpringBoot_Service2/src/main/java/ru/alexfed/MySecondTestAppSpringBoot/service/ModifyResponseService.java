package ru.alexfed.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.alexfed.MySecondTestAppSpringBoot.model.Response;
@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
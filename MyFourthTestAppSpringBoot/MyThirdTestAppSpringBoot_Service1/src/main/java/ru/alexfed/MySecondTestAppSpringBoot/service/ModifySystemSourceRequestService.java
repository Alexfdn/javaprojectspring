package ru.alexfed.MySecondTestAppSpringBoot.service;
import org.springframework.stereotype.Service;
import ru.alexfed.MySecondTestAppSpringBoot.model.Request;
@Service
public class ModifySystemSourceRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setSource("change source");
    }
}


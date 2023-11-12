package ru.alexfed.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.alexfed.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
    double quarterlyBonus(Positions positions, double salary, double bonus, int workDays);
}


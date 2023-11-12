package ru.alexfed.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.alexfed.MySecondTestAppSpringBoot.model.Positions;
import java.util.Calendar;
import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    private Integer year = Year.now().getValue();

    public static int DaysYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    }
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {

        return salary * bonus * DaysYear(this.year) * positions.getPositionCoefficient() / workDays;
    }
    @Override
    public double quarterlyBonus(Positions positions, double salary, double bonus, int workDays) {
        double result = 0.00;

        if (positions.isManager()) {
            result = salary * bonus * DaysYear(this.year) * positions.getPositionCoefficient() / workDays / 4;
        }
        return result;
    }
}



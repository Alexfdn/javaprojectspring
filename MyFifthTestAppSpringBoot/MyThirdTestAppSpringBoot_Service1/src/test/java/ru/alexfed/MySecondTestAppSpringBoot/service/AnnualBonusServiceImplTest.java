package ru.alexfed.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.alexfed.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void quarterlyBonus() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        double result = new AnnualBonusServiceImpl().quarterlyBonus(position, salary, bonus, workDays);

        double expected = 90123.45679012345;
        assertThat(result).isEqualTo(expected);
    }
}

package ru.alexfed.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    AN(2.0, false),
    QA(1.7, false),
    TS(1.2, false);
    private final double positionCoefficient;
    private final boolean isManager;
    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}

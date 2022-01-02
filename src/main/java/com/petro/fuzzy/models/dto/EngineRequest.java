package com.petro.fuzzy.models.dto;

import com.petro.fuzzy.models.enums.DefuzzifyMethod;

public record EngineRequest(double engineSpeed, double vacuumPressure,
                            DefuzzifyMethod defuzzifyMethod) {

}

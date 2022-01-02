package com.petro.fuzzy.services;

import com.petro.fuzzy.models.control.FuzzyControllerWrapper;
import com.petro.fuzzy.models.dto.EngineRequest;
import com.petro.fuzzy.models.dto.EngineResponse;
import fuzzy4j.flc.InputInstance;
import org.springframework.stereotype.Service;

@Service
public class EngineControlService {

  public EngineResponse proceedSignal(EngineRequest request) {
    final var fuzzyControllerWrapper = new FuzzyControllerWrapper(request.defuzzifyMethod());

    final var instance = new InputInstance()
        .is(fuzzyControllerWrapper.getSpeed(), request.engineSpeed())
        .is(fuzzyControllerWrapper.getVacuumPressure(), request.vacuumPressure());

    final var crisp = fuzzyControllerWrapper.getController().apply(instance);

    return new EngineResponse(request.engineSpeed(), request.vacuumPressure(),
        crisp.get(fuzzyControllerWrapper.getFpw()));
  }

}

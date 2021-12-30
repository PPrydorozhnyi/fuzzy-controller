package com.petro.fuzzy.services;

import com.petro.fuzzy.models.EngineRequest;
import com.petro.fuzzy.models.EngineResponse;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class EngineControlService {

  public EngineResponse proceedSignal(EngineRequest request) {
    return new EngineResponse(request.engineSpeed(), request.vacuumPressure(),
        new Random().nextInt());
  }

}

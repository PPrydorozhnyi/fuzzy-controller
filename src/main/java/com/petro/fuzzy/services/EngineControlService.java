package com.petro.fuzzy.services;

import static fuzzy4j.flc.Term.term;
import static fuzzy4j.flc.Variable.input;
import static fuzzy4j.flc.Variable.output;

import com.petro.fuzzy.models.EngineRequest;
import com.petro.fuzzy.models.EngineResponse;
import fuzzy4j.flc.ControllerBuilder;
import fuzzy4j.flc.FLC;
import fuzzy4j.flc.InputInstance;
import fuzzy4j.flc.Variable;
import org.springframework.stereotype.Service;

@Service
public class EngineControlService {

  private final FLC impl;
  private final Variable vacuumPressure;
  private final Variable speed;
  private final Variable fpw;

  public EngineControlService() {
    final var sLow = term("low", -1, 0, 1500, 2000);
    final var sMed   = term("med", 1600, 2000, 2500, 2700);
    final var sHigh  = term("high", 2500, 2800, 3000, 3100);

    speed = input("engineSpeed", sLow, sMed, sHigh).start(0).end(3000);

    final var vVLow = term("vlow", -1, 0, 2, 6);
    final var vLow = term("low", 3, 6, 9, 12);
    final var vHigh  = term("high", 10, 12, 18, 20);
    final var vVHigh  = term("vhigh", 18, 22, 40, 41);

    vacuumPressure = input("vacuumPressure", vVLow, vLow, vHigh, vVHigh)
        .start(0).end(40);

    final var vsmall  = term("vsmall", -1, 0, 135, 142);
    final var small = term("small", 141, 142, 142.8, 143.5);
    final var large = term("large", 143.2, 143.5, 143.7, 144.1);
    final var vLarge = term("vLarge", 143.9, 144.2, 145, 146);

    fpw = output("fpw", vsmall, small, large, vLarge).start(133).end(145);

    impl = ControllerBuilder.newBuilder()
        .when().var(speed).is(sLow).and().var(vacuumPressure).is(vVHigh).then().var(fpw).is(vsmall)
        .when().var(speed).is(sMed).and().var(vacuumPressure).is(vVHigh).then().var(fpw).is(vsmall)
        .when().var(speed).is(sHigh).and().var(vacuumPressure).is(vVHigh).then().var(fpw).is(vsmall)
        .when().var(speed).is(sLow).and().var(vacuumPressure).is(vHigh).then().var(fpw).is(vsmall)
        .when().var(speed).is(sMed).and().var(vacuumPressure).is(vHigh).then().var(fpw).is(small)
        .when().var(speed).is(sHigh).and().var(vacuumPressure).is(vHigh).then().var(fpw).is(small)
        .when().var(speed).is(sLow).and().var(vacuumPressure).is(vLow).then().var(fpw).is(small)
        .when().var(speed).is(sLow).and().var(vacuumPressure).is(vVLow).then().var(fpw).is(small)
        .when().var(speed).is(sMed).and().var(vacuumPressure).is(vLow).then().var(fpw).is(large)
        .when().var(speed).is(sMed).and().var(vacuumPressure).is(vVLow).then().var(fpw).is(vLarge)
        .when().var(speed).is(sHigh).and().var(vacuumPressure).is(vLow).then().var(fpw).is(vLarge)
        .when().var(speed).is(sHigh).and().var(vacuumPressure).is(vVLow).then().var(fpw).is(vLarge)
        .create();
  }

  public EngineResponse proceedSignal(EngineRequest request) {

    final var instance = new InputInstance()
        .is(speed, request.engineSpeed())
        .is(vacuumPressure, request.vacuumPressure());

    final var crisp = impl.apply(instance);

    return new EngineResponse(request.engineSpeed(), request.vacuumPressure(),
        crisp.get(fpw));
  }

}

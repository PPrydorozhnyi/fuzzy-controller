package com.petro.fuzzy.controllers;

import com.petro.fuzzy.models.dto.EngineRequest;
import com.petro.fuzzy.models.dto.EngineResponse;
import com.petro.fuzzy.services.EngineControlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FuzzyController {

  private final EngineControlService engineControlService;

  @MessageMapping("/signal")
  @SendTo("/topic/fpw")
  public EngineResponse proceedSignal(EngineRequest request) {
    log.info("Request: {}", request);
    final var engineResponse = engineControlService.proceedSignal(request);
    log.info("Response: {}", engineResponse);
    return engineResponse;
  }

}

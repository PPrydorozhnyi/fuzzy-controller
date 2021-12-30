package com.petro.fuzzy.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * About performance according to Spring doc:
 * The obvious place to start is to configure the thread pools backing the
 * "clientInboundChannel" and the "clientOutboundChannel".
 * By default both are configured at twice the number of available processors.
 *
 * <p>https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference
 * /html/websocket.html#websocket-stomp-configuration-performance
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/fuzzy")
        .setAllowedOriginPatterns("*")
        .withSockJS();
  }

}

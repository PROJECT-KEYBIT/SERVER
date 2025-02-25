package com.msa.order.config;

import com.msa.order.application.port.out.EventOutputPort;
import com.msa.order.common.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {

    private final EventOutputPort eventOutputPort;

    @Bean
    public InitializingBean eventInitializer() {
        return () -> Events.setPublisher(eventOutputPort);
    }
}

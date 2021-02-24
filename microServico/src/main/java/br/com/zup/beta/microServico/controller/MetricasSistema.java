package br.com.zup.beta.microServico.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MetricasSistema {

    private final MeterRegistry meterRegistry;
    
    public MetricasSistema(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
}

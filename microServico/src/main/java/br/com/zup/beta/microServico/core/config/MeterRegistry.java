package br.com.zup.beta.microServico.core.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Tag;

import java.util.ArrayList;
import java.util.Collection;

public class MeterRegistry {

    private io.micrometer.core.instrument.MeterRegistry meterRegistry;

    public void meuContador() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);

        contadorDePropostasCriadas.increment();
    }

}

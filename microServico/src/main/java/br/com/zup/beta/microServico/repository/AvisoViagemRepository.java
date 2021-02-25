package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.viagem.AvisoViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}

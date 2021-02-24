package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.bloqueio.BloqueioCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository  extends JpaRepository<BloqueioCartao, Long> {

}

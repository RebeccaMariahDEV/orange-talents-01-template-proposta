package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.proporsta.NovaProposta;
import br.com.zup.beta.microServico.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long> {

    Optional<NovaProposta> findByDocumentoId (String documentoId);
    //pesquisa dupla com and no jpa
    List<NovaProposta> findByStatusAndProcessado (Status status, boolean processado);



}


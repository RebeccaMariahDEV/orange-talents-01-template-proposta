package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.NovaProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long> {

    Optional<NovaProposta> findByDocumentoId (String documentoId);
    

}


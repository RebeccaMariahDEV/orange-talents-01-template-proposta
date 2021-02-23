package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoesGeradosRepository extends JpaRepository<CartoesGerados, Long> {

}

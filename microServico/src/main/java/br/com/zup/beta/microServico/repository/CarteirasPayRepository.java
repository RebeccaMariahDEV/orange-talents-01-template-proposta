package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.carteirasPay.CarteirasPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteirasPayRepository extends JpaRepository<CarteirasPay, Long> {
}

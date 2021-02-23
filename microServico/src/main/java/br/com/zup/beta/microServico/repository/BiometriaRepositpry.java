package br.com.zup.beta.microServico.repository;

import br.com.zup.beta.microServico.model.biometria.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BiometriaRepositpry extends JpaRepository<Biometria, Long> {

        Optional<Biometria> findByBiometria (String biometria);

}

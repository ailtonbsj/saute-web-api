package ailtonbsj.sauteweb.sauteapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.entities.NivelEscolar;

public interface NivelEscolarRepository extends JpaRepository<NivelEscolar, Long> {
    List<NivelEscolar> findByNivelEscolarContainingIgnoreCase(String nivelEscolar);
}

package ailtonbsj.sauteweb.sauteapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.model.NivelEscolar;

public interface NivelEscolarRepository extends JpaRepository<NivelEscolar, Long> {
    List<NivelEscolar> findByNivelEscolarContainingIgnoreCase(String nivelEscolar);
}

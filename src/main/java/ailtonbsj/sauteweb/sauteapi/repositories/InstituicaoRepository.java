package ailtonbsj.sauteweb.sauteapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.entities.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Iterable<Instituicao> findByInstituicaoContainingIgnoreCase(String instituicao);
}

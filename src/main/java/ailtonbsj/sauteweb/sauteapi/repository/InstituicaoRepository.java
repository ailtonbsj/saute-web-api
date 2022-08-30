package ailtonbsj.sauteweb.sauteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Iterable<Instituicao> findByInstituicaoContainingIgnoreCase(String instituicao);
}

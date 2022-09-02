package ailtonbsj.sauteweb.sauteapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.entities.Autorizacao;
import ailtonbsj.sauteweb.sauteapi.entities.Processo;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
    public Iterable<Autorizacao> findAllByProcesso(Processo p);

    public Iterable<Autorizacao> findAllByProcessoId(Long p);
}

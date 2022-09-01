package ailtonbsj.sauteweb.sauteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.model.Autorizacao;
import ailtonbsj.sauteweb.sauteapi.model.Processo;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
    public Iterable<Autorizacao> findAllByProcesso(Processo p);

    public Iterable<Autorizacao> findAllByProcessoId(Long p);
}

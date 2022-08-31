package ailtonbsj.sauteweb.sauteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    public Iterable<Processo> findByNumeroContaining(String numero);
}

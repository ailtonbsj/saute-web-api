package ailtonbsj.sauteweb.sauteapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.entities.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    public Iterable<Processo> findByNumeroContaining(String numero);
}

package ailtonbsj.sauteweb.sauteapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Iterable<Professor> findByNomeContainingIgnoreCase(String professor);
}

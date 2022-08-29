package ailtonbsj.sauteweb.sauteapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ailtonbsj.sauteweb.sauteapi.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Iterable<Professor> findByNomeContainingIgnoreCase(String professor);
}

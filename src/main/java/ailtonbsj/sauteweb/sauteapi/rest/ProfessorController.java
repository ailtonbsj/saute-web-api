package ailtonbsj.sauteweb.sauteapi.rest;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.model.Professor;
import ailtonbsj.sauteweb.sauteapi.repository.ProfessorRepository;
import ailtonbsj.sauteweb.sauteapi.utils.Utils;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin("http://localhost:4200")
public class ProfessorController {

    @Autowired
    private ProfessorRepository rep;

    @PostMapping
    public Long save(@RequestBody Professor professor) {
        return rep.save(professor).getId();
    }

    @GetMapping("{id}")
    public Professor findById(@PathVariable Long id) {
        return rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Iterable<Professor> index(@RequestParam Optional<String> q) {
        if (q.isEmpty())
            return rep.findAll();
        else
            return rep.findByNomeContainingIgnoreCase(q.get());
    }

    @PatchMapping
    public Long update(@RequestBody Professor professor) {
        Professor ent = rep.findById(professor.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        professor.setId(null);
        BeanUtils.copyProperties(professor, ent, Utils.getNullPropertyNames(professor));
        ent.setUpdatedAt(LocalDateTime.now());
        return rep.save(ent).getId();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        rep.deleteById(id);
    }

}

package ailtonbsj.sauteweb.sauteapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.model.Professor;
import ailtonbsj.sauteweb.sauteapi.repository.ProfessorRepository;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin("http://localhost:4200")
public class ProfessorController {

    @Autowired
    private ProfessorRepository rep;

    @PostMapping
    public Professor save(@RequestBody Professor professor) {
        return rep.save(professor);
    }

    @GetMapping("{id}")
    public Professor findById(@PathVariable Long id) {
        return rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

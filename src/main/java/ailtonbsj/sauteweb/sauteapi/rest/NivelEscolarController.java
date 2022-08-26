package ailtonbsj.sauteweb.sauteapi.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.model.NivelEscolar;
import ailtonbsj.sauteweb.sauteapi.repository.NivelEscolarRepository;

@RestController
@RequestMapping("/api/nivelescolar")
public class NivelEscolarController {

    @Autowired
    private NivelEscolarRepository repository;

    @GetMapping
    public List<NivelEscolar> index() {
        return repository.findAll();
    }

    @PostMapping
    public NivelEscolar store(@RequestBody NivelEscolar nivelEscolar) {
        return repository.save(nivelEscolar);
    }

    @GetMapping("{id}")
    public NivelEscolar show(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public NivelEscolar update(@RequestBody NivelEscolar nivelEscolar) {
        NivelEscolar ent = repository.findById(nivelEscolar.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ent.setNivelEscolar(nivelEscolar.getNivelEscolar());
        ent.setUpdatedAt(LocalDateTime.now());
        return repository.save(ent);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

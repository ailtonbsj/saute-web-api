package ailtonbsj.sauteweb.sauteapi.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

import ailtonbsj.sauteweb.sauteapi.model.NivelEscolar;
import ailtonbsj.sauteweb.sauteapi.repository.NivelEscolarRepository;

@RestController
@RequestMapping("/api/nivelescolar")
@CrossOrigin("http://localhost:4200")
public class NivelEscolarController {

    @Autowired
    private NivelEscolarRepository repository;

    @GetMapping
    public List<NivelEscolar> index(@RequestParam Optional<String> q) {
        if (q.isEmpty())
            return repository.findAll();
        else
            return repository.findByNivelEscolarContainingIgnoreCase(q.get());
    }

    @PostMapping
    public Long store(@RequestBody NivelEscolar nivelEscolar) {
        return repository.save(nivelEscolar).getId();
    }

    @GetMapping("{id}")
    public NivelEscolar show(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PatchMapping
    public Long update(@RequestBody NivelEscolar nivelEscolar) {
        NivelEscolar ent = repository.findById(nivelEscolar.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ent.setNivelEscolar(nivelEscolar.getNivelEscolar());
        ent.setUpdatedAt(LocalDateTime.now());
        return repository.save(ent).getId();
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

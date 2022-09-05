package ailtonbsj.sauteweb.sauteapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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

import ailtonbsj.sauteweb.sauteapi.dto.NivelEscolarDTO;
import ailtonbsj.sauteweb.sauteapi.services.NivelEscolarService;

@RestController
@RequestMapping("/api/nivelescolar")
@CrossOrigin({"${app.front-url}"})
public class NivelEscolarController {

    @Autowired
    NivelEscolarService service;

    @PreAuthorize("hasRole('viewer')")
    @GetMapping
    public Iterable<NivelEscolarDTO> index(@RequestParam Optional<String> q) {
        if (q.isEmpty())
            return service.findAll();
        else
            return service.findByNivelEscolar(q.get());
    }

    @PreAuthorize("hasRole('editor')")
    @PostMapping
    public Long store(@RequestBody NivelEscolarDTO nivelEscolar) {
        return service.save(nivelEscolar);
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping("{id}")
    public NivelEscolarDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasRole('editor')")
    @PatchMapping
    public Long update(@RequestBody NivelEscolarDTO nivelEscolar) {
        return service.update(nivelEscolar);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        service.deleteById(id);
    }

}

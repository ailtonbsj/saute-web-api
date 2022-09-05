package ailtonbsj.sauteweb.sauteapi.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.entities.Processo;
import ailtonbsj.sauteweb.sauteapi.repositories.ProcessoRepository;
import ailtonbsj.sauteweb.sauteapi.utils.Utils;

@RestController
@RequestMapping("/api/processo")
@CrossOrigin({"${app.front-url}"})
public class ProcessoController {

    @Autowired
    ProcessoRepository rep;

    @PreAuthorize("hasRole('editor')")
    @PostMapping
    public Long save(@RequestBody Processo processo) {
        return rep.save(processo).getId();
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping
    public Iterable<Processo> findAll(@RequestParam Optional<String> q) {
        if (q.isEmpty()) {
            return rep.findAll();
        } else {
            System.out.println(q.get());
            return rep.findByNumeroContaining(q.get());
        }
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping("{id}")
    public Processo findById(@PathVariable Long id) {
        return rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('editor')")
    @PatchMapping
    public Long update(@RequestBody Processo processo) {
        Processo ent = rep.findById(processo.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        processo.setId(null);
        BeanUtils.copyProperties(processo, ent, Utils.getNullPropertyNames(processo));
        ent.setUpdatedAt(LocalDateTime.now());
        return rep.save(ent).getId();
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        rep.deleteById(id);
    }

}

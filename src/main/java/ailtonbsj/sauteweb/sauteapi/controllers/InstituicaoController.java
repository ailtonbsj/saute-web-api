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

import ailtonbsj.sauteweb.sauteapi.entities.Instituicao;
import ailtonbsj.sauteweb.sauteapi.repositories.InstituicaoRepository;
import ailtonbsj.sauteweb.sauteapi.utils.Utils;

@RestController
@RequestMapping("/api/instituicao")
@CrossOrigin({"${app.front-url}"})
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository rep;

    @PreAuthorize("hasRole('editor')")
    @PostMapping
    public Long save(@RequestBody Instituicao instituicao) {
        return rep.save(instituicao).getId();
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping
    public Iterable<Instituicao> findAll(@RequestParam Optional<String> q) {
        if (q.isEmpty())
            return rep.findAll();
        else
            return rep.findByInstituicaoContainingIgnoreCase(q.get());
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping("{id}")
    public Instituicao findById(@PathVariable Long id) {
        return rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('editor')")
    @PatchMapping
    public Long update(@RequestBody Instituicao instituicao) {
        Instituicao ent = rep.findById(instituicao.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        instituicao.setId(null);
        BeanUtils.copyProperties(instituicao, ent, Utils.getNullPropertyNames(instituicao));
        ent.setUpdatedAt(LocalDateTime.now());
        return rep.save(ent).getId();
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        rep.deleteById(id);
    }
}

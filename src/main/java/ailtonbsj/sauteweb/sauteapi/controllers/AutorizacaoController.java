package ailtonbsj.sauteweb.sauteapi.controllers;

import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.entities.Autorizacao;
import ailtonbsj.sauteweb.sauteapi.repositories.AutorizacaoRepository;
import ailtonbsj.sauteweb.sauteapi.utils.Utils;

@RestController
@RequestMapping("/api/autorizacao")
@CrossOrigin({"${app.front-url}"})
public class AutorizacaoController {

    @Autowired
    AutorizacaoRepository rep;

    @PreAuthorize("hasRole('editor')")
    @PostMapping
    public Long save(@RequestBody Autorizacao autorizacao) {
        return rep.save(autorizacao).getId();
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping
    public Iterable<Autorizacao> findAll() {
        return rep.findAll();
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping("/proc/{id}")
    public Iterable<Autorizacao> findAllByProcesso(@PathVariable Long id) {
        return rep.findAllByProcessoId(id);
    }

    @PreAuthorize("hasRole('viewer')")
    @GetMapping("{id}")
    public Autorizacao findById(@PathVariable Long id) {
        return rep.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('editor')")
    @PatchMapping
    public Long update(@RequestBody Autorizacao autorizacao) {
        Autorizacao ent = this.findById(autorizacao.getId());
        autorizacao.setId(null);
        BeanUtils.copyProperties(autorizacao, ent, Utils.getNullPropertyNames(autorizacao));
        ent.setUpdatedAt(LocalDateTime.now());
        return rep.save(ent).getId();
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        rep.deleteById(id);
    }

}

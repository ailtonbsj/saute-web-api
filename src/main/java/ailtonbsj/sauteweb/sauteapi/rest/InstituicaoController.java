package ailtonbsj.sauteweb.sauteapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.model.Instituicao;
import ailtonbsj.sauteweb.sauteapi.model.NivelEscolar;
import ailtonbsj.sauteweb.sauteapi.repository.InstituicaoRepository;
import ailtonbsj.sauteweb.sauteapi.repository.NivelEscolarRepository;

@RestController
@RequestMapping("/api/instituicao")
@CrossOrigin("http://localhost:4200")
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository rep;

    @PostMapping
    public Long save(@RequestBody Instituicao instituicao) {
        return rep.save(instituicao).getId();
    }

    @GetMapping
    public Iterable<Instituicao> findAll() {
        return rep.findAll();
    }

    @PatchMapping
    public Instituicao update(@RequestBody Instituicao instituicao) {
        rep.findById(instituicao.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return rep.save(instituicao);

        // Professor ent = rep.findById(professor.getId()).orElseThrow(
        // () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // professor.setId(null);
        // BeanUtils.copyProperties(professor, ent,
        // Utils.getNullPropertyNames(professor));
        // ent.setUpdatedAt(LocalDateTime.now());
        // return rep.save(ent).getId();
    }

    // @PostMapping
    // public Instituicao save(@RequestBody Instituicao instituicao) {
    // Instituicao i = rep.save(instituicao);
    // i.setNivelEscolar(repNivel.findById(i.getNivelEscolar().getId()).orElseThrow());
    // return i;
    // }
}

package ailtonbsj.sauteweb.sauteapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ailtonbsj.sauteweb.sauteapi.model.Instituicao;
import ailtonbsj.sauteweb.sauteapi.repository.InstituicaoRepository;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoRepository rep;

    public Instituicao save(@RequestBody Instituicao instituicao) {
        return rep.save(instituicao);
    }
}

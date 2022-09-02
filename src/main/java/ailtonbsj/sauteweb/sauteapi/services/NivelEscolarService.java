package ailtonbsj.sauteweb.sauteapi.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ailtonbsj.sauteweb.sauteapi.dto.NivelEscolarDTO;
import ailtonbsj.sauteweb.sauteapi.entities.NivelEscolar;
import ailtonbsj.sauteweb.sauteapi.repositories.NivelEscolarRepository;

@Service
public class NivelEscolarService {
    @Autowired
    private NivelEscolarRepository repository;

    public Iterable<NivelEscolarDTO> findAll() {
        return repository.findAll().stream().map(ent -> {
            return new NivelEscolarDTO(ent);
        }).collect(Collectors.toList());
    }

    public NivelEscolarDTO findById(Long id) {
        NivelEscolar ent = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new NivelEscolarDTO(ent);
    }

    public Iterable<NivelEscolarDTO> findByNivelEscolar(String q) {
        return repository.findByNivelEscolarContainingIgnoreCase(q).stream().map(ent -> {
            return new NivelEscolarDTO(ent);
        }).collect(Collectors.toList());
    }

    public Long save(NivelEscolarDTO dto) {
        NivelEscolar ent = new NivelEscolar();
        ent.setNivelEscolar(dto.getNivelEscolar());
        return repository.save(ent).getId();
    }

    public Long update(NivelEscolarDTO dto) {
        NivelEscolar ent = repository.findById(dto.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ent.setNivelEscolar(dto.getNivelEscolar());
        ent.setUpdatedAt(LocalDateTime.now());
        return repository.save(ent).getId();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

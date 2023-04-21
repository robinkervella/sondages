package fr.simplon.sondages.controller;

import fr.simplon.sondages.entity.Sondage;
import fr.simplon.sondages.repository.SondageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sondages")
public class SondageController {

    @Autowired
    private SondageRepository sondageRepository;

    @GetMapping
    public List<Sondage> getSondages() {
        return sondageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sondage> getSondage(@PathVariable Long id) {
        Optional<Sondage> sondageOptional = sondageRepository.findById(id);
        return sondageOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSondage(@Valid @RequestBody Sondage sondage) {
        sondage.setCreatedDate(LocalDate.now());
        sondageRepository.save(sondage);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSondage(@PathVariable Long id, @Valid @RequestBody Sondage sondage) {
        Optional<Sondage> sondageOptional = sondageRepository.findById(id);
        if (sondageOptional.isPresent()) {
            sondage.setId(id);
            sondageRepository.save(sondage);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSondage(@PathVariable Long id) {
        Optional<Sondage> sondageOptional = sondageRepository.findById(id);
        if (sondageOptional.isPresent()) {
            sondageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


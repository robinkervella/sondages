package fr.simplon.sondages.repository;

import fr.simplon.sondages.entity.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SondageRepository extends JpaRepository<Sondage, Long> {
    List<Sondage> findByClosingDateAfter(LocalDate currentDate);
}

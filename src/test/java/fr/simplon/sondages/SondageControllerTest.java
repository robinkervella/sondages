package fr.simplon.sondages;

import fr.simplon.sondages.entity.Sondage;
import fr.simplon.sondages.repository.SondageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SondageControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SondageRepository sondageRepository;

    private Sondage sondage;

    @BeforeEach
    public void setUp() {
        // Création d'un sondage pour les tests
        sondage = new Sondage();
        sondage.setDescription("Test description");
        sondage.setQuestion("Test question");
        sondage.setCreatedDate(LocalDate.now());
        sondage.setClosingDate(LocalDate.now().plusDays(7));
        sondage.setCreatedBy("Test user");
        sondageRepository.save(sondage);
    }

    @AfterEach
    public void tearDown() {
        // Suppression du sondage créé pour les tests
        sondageRepository.deleteById(sondage.getId());
    }

    @Test
    public void testGetSondages() {
        ResponseEntity<List<Sondage>> responseEntity =
                restTemplate.exchange("/sondages", HttpMethod.GET, null, new ParameterizedTypeReference<List<Sondage>>() {});
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testGetSondage() {
        ResponseEntity<Sondage> responseEntity =
                restTemplate.exchange("/sondages/" + sondage.getId(), HttpMethod.GET, null, Sondage.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testCreateSondage() {

        sondage.setDescription("New description");
        sondage.setQuestion("New question");
        sondage.setCreatedDate(LocalDate.now());
        sondage.setClosingDate(LocalDate.now().plusDays(7));
        sondage.setCreatedBy("New user");
        HttpEntity<Sondage> request = new HttpEntity<>(sondage);
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/sondages", HttpMethod.POST, request, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateSondage() {
        sondage.setDescription("Updated description");
        sondage.setQuestion("Updated question");
        HttpEntity<Sondage> request = new HttpEntity<>(sondage);
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/sondages/" + sondage.getId(), HttpMethod.PUT, request, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteSondage() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/sondages/" + sondage.getId(), HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

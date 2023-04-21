package fr.simplon.sondages;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "SondagesOXO",
                description = "" +
                        "Une API REST qui permet à nos collaborateurs de créer, lire, modifier et supprimer des sondages",
                contact = @Contact(
                        name = "Watashi Nakamura",
                        email = "petros.stergioulas94@gmail.com"
                )
)
)
public class SondagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SondagesApplication.class, args);
    }

}

package fr.simplon.sondages.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Sondage {

    public Sondage() {

    }

    public Sondage(String description, String question, String createdBy, LocalDate createdDate, LocalDate closingDate) {
        this.description = description;
        this.question = question;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.closingDate = closingDate;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description est obligatoire")
    @Size(min = 3, max = 120, message = "La description doit contenir entre 3 et 120 caractères")
    private String description;

    @NotBlank(message = "La question est obligatoire")
    @Size(max = 120, message = "La question doit contenir au maximum 120 caractères")
    private String question;

    @NotBlank(message = "Le nom du créateur est obligatoire")
    @Size(max = 64, message = "Le nom du créateur doit contenir au maximum 64 caractères")
    private String createdBy;

    @NotNull(message = "La date de création est obligatoire")
    private LocalDate createdDate;

    @NotNull(message = "La date de clôture est obligatoire")
    @Future(message = "La date de clôture doit être dans le futur")
    private LocalDate closingDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }
}

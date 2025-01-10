package org.example.gestionproduitonline.web.rest.dto;

import java.time.LocalDateTime;

public class CommentaireDTO {
    private Long id;
    private String contenu;
    private LocalDateTime dateCreation;
    private Long utilisateurId;
    private Long produitId;

    public CommentaireDTO() {}

    public CommentaireDTO(Long id, String contenu, LocalDateTime dateCreation, Long utilisateurId, Long produitId) {
        this.id = id;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.utilisateurId = utilisateurId;
        this.produitId = produitId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }
}

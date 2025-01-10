package org.example.gestionproduitonline.web.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RatingDTO {
    private Long produitId;
    private Long utilisateurId;
    @Min(1)
    @Max(5)
    private int score;

    public RatingDTO() {}

    public RatingDTO(Long produitId, Long utilisateurId, int score) {
        this.produitId = produitId;
        this.utilisateurId = utilisateurId;
        this.score = score;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Min(1)
    @Max(5)
    public int getScore() {
        return score;
    }

    public void setScore(@Min(1) @Max(5) int score) {
        this.score = score;
    }
}


package org.example.gestionproduitonline.web.rest.dto;

public class ProductDTO {
    private Long id;
    private String nom;
    private String description;
    private Long prix;
    private Long quantiteStock;
    private String categorie;

    public ProductDTO() {}

    public ProductDTO(Long id, String nom, String description, Long prix, Long quantiteStock, String categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public Long getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Long quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}


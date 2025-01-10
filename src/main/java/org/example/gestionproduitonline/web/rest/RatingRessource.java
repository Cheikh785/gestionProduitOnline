package org.example.gestionproduitonline.web.rest;

import org.example.gestionproduitonline.domain.Rating;
import org.example.gestionproduitonline.service.RatingService;
import org.example.gestionproduitonline.web.rest.dto.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingRessource {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody RatingDTO ratingDto) {
        Rating rating = ratingService.addRating(
                ratingDto.getProduitId(),
                ratingDto.getUtilisateurId(),
                ratingDto.getScore()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    /**
     * Moyenne des notes attribuees a un produit
     * @param produitId
     * @return
     */
    @GetMapping("/product/{produitId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long produitId) {
        return ResponseEntity.ok(ratingService.getAverageRatingForProduct(produitId));
    }

    /**
     * Nombre de produits par categorie.
     * @param category
     * @return
     */
    @GetMapping("/category/{category}/count")
    public ResponseEntity<Long> getProductCountByCategory(@PathVariable String category) {
        return ResponseEntity.ok(ratingService.getProductCountByCategory(category));
    }
}


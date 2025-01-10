package org.example.gestionproduitonline.service;

import org.example.gestionproduitonline.domain.Rating;

public interface RatingService {
    Rating addRating(Long produitId, Long utilisateurId, int score);
    Double getAverageRatingForProduct(Long productId);
    Long getProductCountByCategory(String categorie);
}

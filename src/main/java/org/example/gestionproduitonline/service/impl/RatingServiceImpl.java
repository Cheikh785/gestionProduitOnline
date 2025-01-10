package org.example.gestionproduitonline.service.impl;

import org.example.gestionproduitonline.domain.Product;
import org.example.gestionproduitonline.domain.Rating;
import org.example.gestionproduitonline.domain.User;
import org.example.gestionproduitonline.repository.RatingRepository;
import org.example.gestionproduitonline.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating addRating(Long produitId, Long utilisateurId, int score) {
        Rating rating = new Rating();
        rating.setScore(score);
        rating.setProduct(new Product(produitId));
        rating.setUser(new User(utilisateurId));
        return ratingRepository.save(rating);
    }

    public Double getAverageRatingForProduct(Long productId) {
        return ratingRepository.findAverageRatingByProductId(productId);
    }

    public Long getProductCountByCategory(String categorie) {
        return ratingRepository.countProductsByCategory(categorie);
    }
}


package org.example.gestionproduitonline.repository;

import org.example.gestionproduitonline.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByNom(String nom);
}
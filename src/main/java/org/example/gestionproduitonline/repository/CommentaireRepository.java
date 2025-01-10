package org.example.gestionproduitonline.repository;

import org.example.gestionproduitonline.domain.Commentaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    Page<Commentaire> findByProductId(Long produitId, Pageable pageable);
    List<Commentaire> findByUserId(Long userId);
}

package org.example.gestionproduitonline.service;

import org.example.gestionproduitonline.domain.Commentaire;
import org.example.gestionproduitonline.web.rest.dto.CommentaireDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentaireService {
    CommentaireDTO addCommentaire(CommentaireDTO commentaireDTO);

    Page<CommentaireDTO> getCommentairesByProduct(Long productId, Pageable pageable);

    void deleteCommentaire(Long commentaireId, Long userId);
}

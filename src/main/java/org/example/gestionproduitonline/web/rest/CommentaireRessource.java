package org.example.gestionproduitonline.web.rest;

import org.example.gestionproduitonline.service.CommentaireService;
import org.example.gestionproduitonline.web.rest.dto.CommentaireDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/commentaires")
public class CommentaireRessource {

    @Autowired
    private CommentaireService commentaireService;

    /**
     * Publier un commentaire
     * @param commentaireDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<CommentaireDTO> publierCommentaire(@RequestBody CommentaireDTO commentaireDTO) {
        CommentaireDTO createdCommentaire = commentaireService.addCommentaire(commentaireDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommentaire);
    }

    /**
     * // Obtenir une liste paginée de commentaires pour un produit
     * @param productId
     * @param page
     * @param size
     * @return Page<CommentaireDTO>
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<CommentaireDTO>> getCommentairesParProduit(
            @PathVariable Long productId,
            @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommentaireDTO> commentaires = commentaireService.getCommentairesByProduct(productId, pageable);
        return ResponseEntity.ok(commentaires);
    }

    /**
     * // Supprimer un commentaire
     * @param commentaireId
     * @param userId
     * @return
     */
    @DeleteMapping("/{commentaireId}/{userId}")
    public ResponseEntity<Void> supprimerCommentaire(@PathVariable Long commentaireId, @PathVariable Long userId) {
        commentaireService.deleteCommentaire(commentaireId, userId);
        return ResponseEntity.noContent().build();
    }
}
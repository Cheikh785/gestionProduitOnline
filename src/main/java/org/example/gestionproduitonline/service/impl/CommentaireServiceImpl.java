package org.example.gestionproduitonline.service.impl;

import org.example.gestionproduitonline.constants.ErrorCodes;
import org.example.gestionproduitonline.domain.Commentaire;
import org.example.gestionproduitonline.domain.Product;
import org.example.gestionproduitonline.domain.User;
import org.example.gestionproduitonline.exception.BadRequestException;
import org.example.gestionproduitonline.exception.NotFoundEntityException;
import org.example.gestionproduitonline.repository.CommentaireRepository;
import org.example.gestionproduitonline.repository.ProductRepository;
import org.example.gestionproduitonline.repository.UserRepository;
import org.example.gestionproduitonline.service.CommentaireService;
import org.example.gestionproduitonline.web.rest.dto.CommentaireDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public CommentaireDTO addCommentaire(CommentaireDTO commentaireDTO) {
        Commentaire commentaire = new Commentaire();
        commentaire.setContenu(commentaireDTO.getContenu());
        commentaire.setDateCreation(LocalDateTime.now());

        User user = userRepository.findById(commentaireDTO.getUtilisateurId())
                .orElseThrow(() -> new NotFoundEntityException(ErrorCodes.PRODUCT_NOT_FOUND.toString(), "User not found "));

        Product product = productRepository.findById(commentaireDTO.getProduitId())
                .orElseThrow(() -> new NotFoundEntityException(ErrorCodes.PRODUCT_NOT_FOUND.toString(), "Product not found"));

        commentaire.setUser(user);
        commentaire.setProduct(product);

        Commentaire savedCommentaire = commentaireRepository.save(commentaire);
        return mapToDTO(savedCommentaire);
    }

    public Page<CommentaireDTO> getCommentairesByProduct(Long productId, Pageable pageable) {
        Page<Commentaire> commentaires = commentaireRepository.findByProductId(productId, pageable);
        return commentaires.map(this::mapToDTO);
    }

    public void deleteCommentaire(Long commentaireId, Long userId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new NotFoundEntityException(ErrorCodes.PRODUCT_NOT_FOUND.toString(), "Commentaire not found"));

        if (!commentaire.getUser().getId().equals(userId)) {
            throw new BadRequestException(ErrorCodes.NOT_ALLOWED.toString(), "Vous n'êtes pas autorisé à supprimer ce commentaire");
        }

        commentaireRepository.delete(commentaire);
    }

    private CommentaireDTO mapToDTO(Commentaire commentaire) {
        CommentaireDTO dto = new CommentaireDTO();
        dto.setId(commentaire.getId());
        dto.setContenu(commentaire.getContenu());
        dto.setDateCreation(commentaire.getDateCreation());
        dto.setUtilisateurId(commentaire.getUser().getId());
        dto.setProduitId(commentaire.getProduct().getId());
        return dto;
    }
}


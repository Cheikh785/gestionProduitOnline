package org.example.gestionproduitonline.service;

import org.example.gestionproduitonline.domain.Product;
import org.example.gestionproduitonline.web.rest.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    /**
     * Save a Product
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    Product save(ProductDTO productDTO);

    /**
     * Update a Product
     *
     * @param product the entity to update
     * @param id the id of the entity to update
     * @return the updated entity.
     */
    Product update(Product product, Long id);

    /**
     * get all Products
     *
     * @return list of products.
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * get a Product by id
     *
     * @param id the id of the entity.
     * @return the entity
     */
    Optional<Product> findOne(Long id);

    /**
     * get a Product by name
     *
     * @param name the name of the user.
     * @return the entity
     */
    Optional<Product> findProductByName(String name);

    /**
     * Delete a Product
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package org.example.gestionproduitonline.service.impl;

import org.example.gestionproduitonline.constants.ErrorCodes;
import org.example.gestionproduitonline.domain.Product;
import org.example.gestionproduitonline.exception.AlreadyExistEntityException;
import org.example.gestionproduitonline.exception.NotFoundEntityException;
import org.example.gestionproduitonline.repository.ProductRepository;
import org.example.gestionproduitonline.service.ProductService;
import org.example.gestionproduitonline.web.rest.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepository productRepository;

    @Override
    public Product save(ProductDTO productDTO) {
        if (this.findProductByName(productDTO.getNom()).isPresent()) {
            throw new AlreadyExistEntityException(ErrorCodes.PRODUCT_ALREADY_EXISTS.toString(), "Product already exists : " + productDTO.getNom());
        }
        Product product = new Product();
        product.setNom(productDTO.getNom());
        product.setDescription(productDTO.getDescription());
        product.setPrix(productDTO.getPrix());
        product.setQuantiteStock(productDTO.getQuantiteStock());
        product.setCategorie(productDTO.getCategorie());

        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
        Product productFound = findOne(id).orElseThrow(() -> new NotFoundEntityException(ErrorCodes.PRODUCT_ALREADY_EXISTS.toString(), "No Product found with this id " + id));

        Optional.ofNullable(productFound.getNom()).ifPresent(product::setNom);
        Optional.ofNullable(productFound.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(productFound.getPrix()).ifPresent(product::setPrix);
        Optional.ofNullable(productFound.getQuantiteStock()).ifPresent(product::setQuantiteStock);
        Optional.ofNullable(productFound.getCategorie()).ifPresent(product::setCategorie);

        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return productRepository.findProductByNom(name);
    }

    @Override
    public void delete(Long id) {
        if (this.findOne(id).isPresent()) {
            productRepository.deleteById(id);
        }
    }
}

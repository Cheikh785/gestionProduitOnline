package org.example.gestionproduitonline.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.gestionproduitonline.constants.ErrorCodes;
import org.example.gestionproduitonline.domain.Product;
import org.example.gestionproduitonline.exception.NotFoundEntityException;
import org.example.gestionproduitonline.service.ProductService;
import org.example.gestionproduitonline.web.rest.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1")
public class ProductRessource {
    private final ProductService productService;

    public ProductRessource(ProductService productService) {
        this.productService = productService;
    }

    /**
     * {@code POST  /products} : Create a new Product
     *
     * @param productDTO the product to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new product,
     * or with status {@code 400 (Bad Request)} if the product has already an id.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        Product result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/v1/products")).body(result);
    }

    /**
     * {@code PUT  /products/{id}} : Update an existing product
     *
     * @param product the product to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated product,
     * or with status {@code 400 (Bad Request)} if the product's id is not found,
     * or with status {@code 500 (Internal Server Error)} if the product couldn't be updated.
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id) {
        Product result = productService.update(product, id);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /products} : get all products
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(Pageable pageable) {
        Page<Product> page = this.productService.findAll(pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /products/:id} : get the Product by id.
     *
     * @param id the id of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService
            .findOne(id)
            .orElseThrow(
                    () -> new NotFoundEntityException(ErrorCodes.PRODUCT_NOT_FOUND.toString(), "No product found with this id")
            );
        return ResponseEntity.ok().body(product);
    }

    /**
     * {@code GET  /products/name/:id} : get the Product by name.
     *
     * @param name the id of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/products/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService
                .findProductByName(name)
                .orElseThrow(
                    () -> new NotFoundEntityException(ErrorCodes.PRODUCT_NOT_FOUND.toString(), "No product found with this id")
                );
        return ResponseEntity.ok().body(product);
    }

    /**
     * {@code DELETE  /products/:id} : delete Product by id.
     *
     * @param id the id of the product to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

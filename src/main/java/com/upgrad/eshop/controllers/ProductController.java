package com.upgrad.eshop.controllers;

import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.services.ProductService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.validators.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private APIAuthorizer apiAuthorizer;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> searchProduct(@RequestParam(value = "category", required = false, defaultValue = "") String category,
                                           @RequestParam(value = "direction", required = false, defaultValue = "DESC") String direction,
                                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                           @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "sortBy", required = false, defaultValue = "productId") String sortBy
    ) {
        if (!(sortBy.equals("price") || sortBy.equals("created") || sortBy.equals("productId"))) {
            sortBy = "productId";
        }
        log.debug("Processing search request with search criteria:" +
                "category: " + category + "," +
                "direction: " + direction + "," +
                "name: " + name + "," +
                "pagenumber: " + pageNumber + "," +
                "pageSize: " + pageSize + "," +
                "sortBy: " + sortBy);
        Page<Product> searchedProducts = productService.searchProduct(category, direction, name, pageNumber, pageSize, sortBy);

        log.debug("search complete with " + searchedProducts.getContent().size() + " results");
        return ResponseEntity.ok(searchedProducts);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody Product product,
                                         @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken) throws APIException {

        log.debug("Processing save product request");

        log.debug("Authorizing admin for ROLE_ADMIN. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.ADMIN, jwtToken);
        log.debug("admin authorised for ROLE_ADMIN");

        log.debug("Started save product request validation");
        productValidator.validateProductRequest(product);
        log.debug("Completed save product request validation");

        log.debug("Adding product to database");
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        log.debug("Processing product request for product id " + id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable("id") Long id,
                                               @RequestBody Product product,
                                               @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken) throws ProductNotFoundException, APIException {
        log.debug("Processing update product request");

        log.debug("Authorizing admin for ROLE_ADMIN. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.ADMIN, jwtToken);
        log.debug("admin authorised for ROLE_ADMIN");

        log.debug("Updating product on database");
        return ResponseEntity.ok(productService.updateProductById(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id,
                                               @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken) throws ProductNotFoundException, APIException {
        log.debug("Processing delete product request");

        log.debug("Authorizing admin for ROLE_ADMIN. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.ADMIN, jwtToken);
        log.debug("admin authorised for ROLE_ADMIN");

        log.debug("Deleting product on database");
        productService.deleteProduct(id);
        return ResponseEntity.ok("Successfully Deleted");
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        log.debug("Processing categories request");

        return ResponseEntity.ok(productService.getProductCategories());
    }

}

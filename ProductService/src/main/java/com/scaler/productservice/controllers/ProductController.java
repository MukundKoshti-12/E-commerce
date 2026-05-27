package com.scaler.productservice.controllers;

import com.scaler.productservice.commons.AuthCommons;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController // Defines that below class is a REST CONTROLLER
@RequestMapping("/products") // Defines the url path where the below controller has to trigger
public class ProductController {

    private ProductService productService;
    private AuthCommons authCommons;
    private RestTemplate restTemplate;

    // @Qualifier :- It used to specify which child object should be used for a particular parent reference
    // It is defining which service implementation should be used by the controller when there are more than one implementation of a particular service
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService,  AuthCommons authCommons, RestTemplate restTemplate) {
        this.productService = productService;
        this.authCommons = authCommons;
        this.restTemplate = restTemplate;
    }

//    --------------------------For SelfProduct Service------------------------
//    @GetMapping("{id}/{tokenValue}") // Defines the url path for get request
//    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId, @PathVariable("tokenValue") String tokenValue) throws ProductNotFoundException {
//        ResponseEntity<Product> responseEntity = null;
//        Product product = null;
//
//        if(authCommons.validateToken(tokenValue)){
//            product = productService.getSingleProduct(productId);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        }
//        else{
//            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//
//        return responseEntity;
//    }


//  --------------------------For FakeStoreProduct Service------------------------
    @GetMapping("{id}") // Defines the url path for get request
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity = null;
        Product product = null;

        restTemplate.getForEntity(
                "http://userservice/user/sample",
                void.class
        );

        System.out.println("Product Service Called!");

        product = productService.getSingleProduct(productId);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);

        return responseEntity;
    }


    @GetMapping()
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/title/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductsByTitle(@PathVariable("title") String title,@PathVariable("pageNumber") int pageNumber,@PathVariable("pageSize") int pageSize){
        return productService.getProductsByTitle(title,pageNumber,pageSize);
    }

    @PostMapping() // Defines the url path for post request
    public Product createProduct(@RequestBody Product product) {
        return null;
    }

    @PutMapping("/{id}") // Defines the url path for put request
    public Product replaceProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return null;
    }

    @DeleteMapping("/{id}") // Defines the url path for delete request
    public void deleteProduct(@PathVariable("id") Long productId) {
        return;
    }

    @ExceptionHandler(ProductNotFoundException.class) // Controller level Exception Handler
    public ResponseEntity<String> productNotFoundException() {
        return new ResponseEntity<>(
                "Class Scoped Exception Handler",
                HttpStatus.NOT_FOUND);
    }
}

package com.crud.crudEstudo.controllers;

import com.crud.crudEstudo.domain.product.RequestProduct;
import com.crud.crudEstudo.domain.product.Product;
import com.crud.crudEstudo.domain.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired// injetar dependencia
    private ProductRepository repository;


    @GetMapping
    public ResponseEntity getAllProduct(){
        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrince_in_cents(data.prince_in_cents());
            return ResponseEntity.ok(product);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }else{
            throw new EntityNotFoundException();
        }
    }

}

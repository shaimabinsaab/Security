package com.example.shopping_project.Controller;

import com.example.shopping_project.Dto.ApiRespones;
import com.example.shopping_project.Model.MyUser;
import com.example.shopping_project.Model.Product;
import com.example.shopping_project.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all-product")
    public ResponseEntity getAllproduct(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    @GetMapping("/my-product")
    public ResponseEntity getMyproduct(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(productService.getProductbyId(myUser.getId()));
    }
    @GetMapping("/{id}")
    public ResponseEntity getProductById(Integer id ){
        return ResponseEntity.status(200).body(productService.getProductbyId(id));
    }
    @PostMapping("/add-blog")
    public ResponseEntity addBlog(@RequestBody @Valid Product product, @AuthenticationPrincipal MyUser myUser){
        productService.addProduct(product,myUser.getId());
        return ResponseEntity.status(201).body(new ApiRespones("product Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Product product, Integer id){
        productService.updateProduct(product,id);
        return ResponseEntity.status(200).body(new ApiRespones("product Updated"));
    }
}

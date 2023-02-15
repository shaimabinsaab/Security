package com.example.shopping_project.Service;

import com.example.shopping_project.Advice.ApiExeption;
import com.example.shopping_project.Model.Product;
import com.example.shopping_project.Model.Product;
import com.example.shopping_project.Repositry.ProductRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepositry productRepositry;
    public List<Product> getAllProducts(){
        return productRepositry.findAll();
    }

    public Product getProductbyId(Integer id){
        Product product=productRepositry.findProductById(id);
        if (product==null){
            throw new ApiExeption("product Not Found!");
        }
        return product;
    }


    public void addProduct(Product newProduct,Integer auth){

       productRepositry.save(newProduct);

    }

    public void deleteProduct(Integer id){
        Product Product=productRepositry.findProductById(id);

        if(Product==null){
            throw new ApiExeption("Product has problem");
        }
       productRepositry.delete(Product);
    }


    public void updateProduct(Product product, Integer id){
        Product Product1=productRepositry.findProductById(id);

        Product1.setName(product.getName());
        Product1.setOrders(product.getOrders());
        Product1.setPrice(product.getPrice());

       productRepositry.save(Product1);

    }
}

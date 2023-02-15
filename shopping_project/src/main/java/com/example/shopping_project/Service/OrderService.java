package com.example.shopping_project.Service;

import com.example.shopping_project.Advice.ApiExeption;
import com.example.shopping_project.Model.MyUser;
import com.example.shopping_project.Model.Order;
import com.example.shopping_project.Model.Product;
import com.example.shopping_project.Repositry.OrdersRepositry;
import com.example.shopping_project.Repositry.ProductRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepositry ordersRepositry;
    private final ProductRepositry productRepositry;
    public List<Order> getAllOrders(){
        return ordersRepositry.findAll();
    }

    public Order getOrder(Integer id){
        Order order=ordersRepositry.findOrderById(id);
        if (order==null){
            throw new ApiExeption("order Not Found!");
        }
        return order;
    }


    public void addOrder(Order newOrder){
        Product c =productRepositry.findProductById(newOrder.getId());
        Order calculate=ordersRepositry.findOrderById(newOrder.getId());
        int total=calculate.getQuantity()*c.getPrice();
        newOrder.setTotalPrice(total);
        ordersRepositry.save(newOrder);

    }

    public void deleteOrder(Integer id){
        Order order=ordersRepositry.findOrderById(id);

        if(order.getStatus()=="progress"||order.getStatus()=="complete"){
            throw new ApiExeption("order has problem");
        }
        ordersRepositry.delete(order);
    }


    public void updateOrder(Order order, Integer id){
        Order order1=ordersRepositry.findOrderById(id);

        order1.setQuantity(order.getQuantity());
        order1.setQuantity(order.getQuantity());
        order1.setDateReceived(order.getDateReceived());
        order1.setProduct(order.getProduct());
        order1.setMyUser(order.getMyUser());

        ordersRepositry.save(order1);
    }
}

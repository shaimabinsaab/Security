package com.example.shopping_project.Repositry;

import com.example.shopping_project.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepositry extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);


}

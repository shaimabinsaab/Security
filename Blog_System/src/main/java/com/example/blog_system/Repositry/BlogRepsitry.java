package com.example.blog_system.Repositry;

import com.example.blog_system.Model.Blog;
import com.example.blog_system.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepsitry extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);
    List<Blog> findAllByMyUser(MyUser myUser);

    Blog findBlogByTitle(String title);



}

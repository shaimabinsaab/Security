package com.example.blog_system.Service;

import com.example.blog_system.Advice.ApiException;
import com.example.blog_system.Model.Blog;
import com.example.blog_system.Model.MyUser;
import com.example.blog_system.Repositry.BlogRepsitry;
import com.example.blog_system.Repositry.MyUserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepsitry blogRepsitry;
    private final MyUserRepositry myUserRepositry;

    public List<Blog> getall(){
        return blogRepsitry.findAll();
    }

    public Blog getBlogById(Integer id,Integer auth){
        Blog blog=blogRepsitry.findBlogById(id);
        if(blog==null){
            throw new ApiException("blog not fond");

        }

        if(blog.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to get this Blog!");
        }
        return blog;
    }

    public List<Blog> getMyBlog(Integer auth){
        MyUser myUser=myUserRepositry.findMyUserById(auth);
        List blog=blogRepsitry.findAllByMyUser(myUser);
        if (blog.isEmpty()){
            throw new ApiException("Empty!");
        }
        return blog;
    }
    public void addMyBlog(Blog blog , Integer auth){
        MyUser myUser=myUserRepositry.findMyUserById(auth);
        blog.setMyUser(myUser);

        blogRepsitry.save(blog);
    }

    public void updateMyBlog(Integer id , Blog newblog , Integer auth){
        Blog oldblog=blogRepsitry.findBlogById(id);
        MyUser myUser=myUserRepositry.findMyUserById(auth);
        if (oldblog==null){
            throw new ApiException("blog not found");
        }else if(oldblog.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this blog!");
        }

        newblog.setId(id);
        newblog.setMyUser(myUser);

        blogRepsitry.save(newblog);
    }

    public void deleteMyBlog(Integer id, Integer auth){
        Blog blog=blogRepsitry.findBlogById(id);
        if (blog==null){
            throw new ApiException("Blog not found");
        }else if(blog.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to delete this blog!");
        }

        blogRepsitry.delete(blog);
    }

    public Blog getBlogBytitle(String title,Integer auth){
        Blog blog=blogRepsitry.findBlogByTitle(title);
        if(blog==null){
            throw new ApiException("blog not fond");

        }

        if(blog.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to get this Blog!");
        }
        return blog;
    }

}

package com.example.brandvista.socialposts.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.brandvista.socialposts.model.Post;
import com.example.brandvista.socialposts.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public String createNewPost(@RequestBody Post post) {
        postService.createPost(post);
        return "Post Created Successfully.";
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/get")
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/get/{postId}")
    public Post getPost(@PathVariable("postId") int postId) {
        return postService.getPostbyId(postId);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<String> putMethodName(@PathVariable("postId") int postId, @RequestBody Post updatedPost) {
        Post existingPost = postService.getPostbyId(postId);
        if (existingPost != null) {
            existingPost.setPostContent(updatedPost.getPostContent());
            existingPost.setPostImageUrl(updatedPost.getPostImageUrl());
            existingPost
                    .setDateCreated(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            existingPost.setLocation(updatedPost.getLocation());
            postService.createPost(existingPost);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body("Post Updated Successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Given Post-ID doesnot exist. Please Try Again.");
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deleteQuery(@PathVariable("postId") int postId) {
        if (postService.getPostbyId(postId) != null) {
            postService.deletePost(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Post Deleted Successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post Not Found.");
    }
}

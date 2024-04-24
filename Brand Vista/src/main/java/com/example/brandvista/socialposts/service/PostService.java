package com.example.brandvista.socialposts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.brandvista.socialposts.model.Post;
import com.example.brandvista.socialposts.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostbyId(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }
}

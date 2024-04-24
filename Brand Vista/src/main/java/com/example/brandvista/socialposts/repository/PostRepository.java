package com.example.brandvista.socialposts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.brandvista.socialposts.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}

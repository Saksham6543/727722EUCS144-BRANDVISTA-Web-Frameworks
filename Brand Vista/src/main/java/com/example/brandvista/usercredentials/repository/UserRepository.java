package com.example.brandvista.usercredentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.brandvista.usercredentials.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
